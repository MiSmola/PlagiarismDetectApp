package polsl.plagiarismdetect.demo.service.batch.job.executor;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import polsl.plagiarismdetect.demo.model.domain.*;
import polsl.plagiarismdetect.demo.model.domain.demo.Result;
import polsl.plagiarismdetect.demo.model.repository.*;
import polsl.plagiarismdetect.demo.service.algorithmic.pyinter.NativePython;
import polsl.plagiarismdetect.demo.service.batch.fileResolve.FileResolveService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@RequiredArgsConstructor
@Log
public class JobExecutor {
    @Value("${app.python.binpath}")
    private String python;
    @Value("${app.python.script.levenshtein}")
    private String scriptLevenshtein;
    @Value("${app.python.script.jarowinkler}")
    private String scriptJaroWinkler;

    @Value("${app.python.script.cosine}")
    private String scriptCosineSim;

    private final SubtaskRepository subtaskRepository;
    private final TaskParameterRepository taskParameterRepository;
    private final FileRepository fileRepository;
    private final ReportRepository reportRepository;
    private final TaskRepository taskRepository;
    private final NativePython nativePython;

    private FileResolveService fileResolveService = new FileResolveService();

    public Report report;

    @Scheduled(cron = "${app.schedule.job-executor}")
    public void run() {
        log.info("JobExecutor.run() started");
        List<Subtask> subtasks = subtaskRepository.findAllByStatusEquals(Status.TODO);
        List<Subtask> failedSubtasks = subtaskRepository.findAllByStatusEquals(Status.FAILED);
        for (Subtask failedSubtask : failedSubtasks) {
            if (failedSubtask.getTaskParameter().getRepeat_number() > 0)
                subtasks.add(failedSubtask);
        }
        log.info("JobExecutor.run() pending subtasks: " + subtasks.size());
        if (subtasks.size() > 0) {
            report = Report.builder()
                    .comparisons(new ArrayList<>())
                    .creationDate(new Date())
                    .title("subtask").build();
            subtasks.forEach(this::executeSubtask);

            /* saving report with the comparisons */
            reportRepository.save(report);

            /* updating parent task*/
            updateTaskDone(subtasks);
        }
        log.info("JobExecutor.run() finished");
    }

    private void executeSubtask(Subtask subtask) {

        if (validateRetryAttepmts(subtask).get()) {
            try {
                File source = fileRepository.findById(subtask.getSource().getId())
                        .orElseThrow(() -> new RuntimeException("File with given id: " + subtask.getSource() + " not exists")),
                        target = fileRepository.findById(subtask.getTarget().getId())
                                .orElseThrow(() -> new RuntimeException("File with given id: " + subtask.getTarget() + " not exists"));

                String tmpFileSourcePath = fileResolveService.transform(source.getLocalPath(), source);
                String tmpFileTargetPath = fileResolveService.transform(target.getLocalPath(), target);
                Result resultLeven = executeSubTaskLogic(tmpFileSourcePath, tmpFileTargetPath, scriptLevenshtein);
                Result resultJaroWinkler = executeSubTaskLogic(tmpFileSourcePath, tmpFileTargetPath, scriptJaroWinkler);
                Result resultCosine = executeSubTaskLogic(tmpFileSourcePath, tmpFileTargetPath, scriptCosineSim);
                populateReport(resultLeven, resultJaroWinkler, resultCosine, source, target);

                List<TaskParameter> taskParametersList = taskParameterRepository.findAll();
                taskParametersList.forEach(taskParameter -> {
                    if (subtask.getTaskParameter().equals(taskParameter)) {
                        subtask.setNumberOfAttempts(subtask.getNumberOfAttempts() + 1);
                        if (taskParameter.getRepeat_number() > 0) {
                            taskParameterRepository.save(TaskParameter.builder()
                                    .id(taskParameter.getId())
                                    .location(taskParameter.getLocation())
                                    .antecedent(taskParameter.getAntecedent())
                                    .repeat_number(taskParameter.getRepeat_number() - 1)
                                    .condition(taskParameter.isCondition())
                                    .task(taskParameter.getTask())
                                    .target(taskParameter.getTarget()).build());
                        }
                    }
                });

                updateTaskSuccess(subtask);
                updateSubtaskSuccess(subtask);
            } catch (Exception e) {
                updateTaskFail(subtask);
                updateSubtaskFail(subtask, e.getMessage());
                log.severe(e.getMessage());
            }
        }
    }

    private AtomicBoolean validateRetryAttepmts(Subtask subtask) {
        AtomicBoolean status = new AtomicBoolean(true);
        List<TaskParameter> taskParametersList = taskParameterRepository.findAll();
        taskParametersList.forEach(taskParameter -> {
            if (subtask.getTaskParameter().equals(taskParameter)) {
                if (taskParameter.getRepeat_number().equals(0)) {
                    status.set(false);
                }
            }
        });
        return status;
    }

    private Result executeSubTaskLogic(String source, String target, String script) throws IOException {
        return Result.builder().value(Float.parseFloat(nativePython.performScript(python, script,
                String.valueOf(source), target).get(0))).build();
    }

    private void populateReport(Result resultLeven, Result resultJaroWinkler, Result resultCosine, File source, File target) {
        report.getComparisons().add(Comparison.builder()
                .creationDate(new Date())
                        .levenshteinCoefficient(String.valueOf(resultLeven.getValue()))
                        .jaroWinklerCoefficient(String.valueOf(resultJaroWinkler.getValue()))
                        .cosineSimilarity(String.valueOf(resultCosine.getValue()))
                .source(source)
                .target(target)
                .build());
    }

    private void updateTaskSuccess(Subtask subtask) {
        int successes = subtask.getTaskParameter().getTask().getPopulationProcessedSuccess();
        subtask.getTaskParameter().getTask().setPopulationProcessedSuccess(successes += 1);
        taskRepository.save(subtask.getTaskParameter().getTask());
    }

    public void updateTaskFail(Subtask subtask) {
        int fails = subtask.getTaskParameter().getTask().getPopulationProcessedFailed();
        subtask.getTaskParameter().getTask().setPopulationProcessedFailed(fails += 1);
        taskRepository.save(subtask.getTaskParameter().getTask());
    }

    public void updateTaskDone(List<Subtask> subtasks) {
        Task task = subtasks.get(0).getTaskParameter().getTask();
        //Tune to partially done
        if(task.getPopulationProcessedFailed().equals(task.getPopulationSize())) task.setStatus(Status.FAILED);
        else if(task.getPopulationProcessedSuccess().equals(task.getPopulationSize())) task.setStatus(Status.DONE);
        else task.setStatus(Status.PARTIALLY_DONE);
        task.setFinishDate(new Date());
        task.setReport(report);
        taskRepository.save(task);
    }

    private void updateSubtaskSuccess(Subtask subtask) {
        //subtask.setReport(report);
        subtask.setFinishDate(new Date());
        subtask.setStatus(Status.DONE);
        subtaskRepository.save(subtask);
    }

    private void updateSubtaskFail(Subtask subtask, String message) {
        subtask.setFinishDate(new Date());
        subtask.setStatus(Status.FAILED);
//        subtask.setRemarks(message);
        subtaskRepository.save(subtask);
    }
}
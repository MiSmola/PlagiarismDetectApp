package polsl.plagiarismdetect.demo.service.batch.job.executor;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import polsl.plagiarismdetect.demo.model.domain.*;


import polsl.plagiarismdetect.demo.model.domain.Comparison;
import polsl.plagiarismdetect.demo.model.domain.File;
import polsl.plagiarismdetect.demo.model.domain.Report;
import polsl.plagiarismdetect.demo.model.domain.Status;
import polsl.plagiarismdetect.demo.model.domain.Subtask;
import polsl.plagiarismdetect.demo.model.domain.Task;
import polsl.plagiarismdetect.demo.model.domain.demo.Result;
import polsl.plagiarismdetect.demo.model.repository.FileRepository;
import polsl.plagiarismdetect.demo.model.repository.ReportRepository;
import polsl.plagiarismdetect.demo.model.repository.SubtaskRepository;
import polsl.plagiarismdetect.demo.model.repository.TaskRepository;
import polsl.plagiarismdetect.demo.service.algorithmic.pyinter.PyNativeInter;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log
public class JobExecutor {
    @Value("${app.python.binpath}")
    private String python;
    @Value("${app.python.script.leven}")
    private String scriptLeven;
    @Value("${app.python.script.matcher}")
    private String scriptMatcher;

    private final SubtaskRepository subtaskRepository;
    private final FileRepository fileRepository;
    private final ReportRepository reportRepository;
    private final TaskRepository taskRepository;
    private final PyNativeInter pyNativeInter;

    public Report report;

    /* job executor invocation (dependent on the subtask type - if type == X_X -> invoke ServiceXX.xx) */
    @Scheduled(cron = "${app.schedule.job-executor}")
    public void run() {
        log.info("JobExecutor.run() started");
        List<Subtask> subtasks = subtaskRepository.findAllByStatusEquals(Status.TODO);
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
//            updateTaskDone(subtasks);
        }
        log.info("JobExecutor.run() finished");
    }

    private void executeSubtask(Subtask subtask) {
        try {
//            File source = fileRepository.findById(subtask.getSource())
//                    .orElseThrow(() -> new RuntimeException("File with given id: " + subtask.getSource() + " not exists")),
//                    target = fileRepository.findById(subtask.getTarget())
//                            .orElseThrow(() -> new RuntimeException("File with given id: " + subtask.getTarget() + " not exists"));
//
//            Result resultLeven = executeSubTaskLogic(source, target, scriptLeven);
//            Result resultMatcher = executeSubTaskLogic(source, target, scriptMatcher);
//            populateReport(resultLeven, resultMatcher, source, target);
//            updateTaskSuccess(subtask);
            updateSubtaskSuccess(subtask);
        } catch (Exception e) {
//            updateTaskFail(subtask);
            updateSubtaskFail(subtask, e.getMessage());
            log.severe(e.getMessage());
        }
    }

    private Result executeSubTaskLogic(File source, File target, String script) throws IOException {
        //TODO: "\"" introduced to handle multiply lines file
        return Result.builder().value(Integer.parseInt(pyNativeInter.performScript(python, script,
                source.getLocalPath(), target.getLocalPath()).get(0))).build();
    }

    private void populateReport(Result resultLeven, Result resultMatcher, File source, File target) {
//        report.getComparisons().add(Comparison.builder()
//                .creationDate(new Date())
//                .levenshteinCoefficient(String.valueOf(resultLeven.getValue()))
//                .matcherCoefficient(String.valueOf(resultMatcher.getValue()))
//                .source(source)
//                .target(target)
//                .build());
    }

//    private void updateTaskSuccess(Subtask subtask) {
//        int successes = subtask.getTask().getPopulationProcessedSuccess();
//        subtask.getTask().setPopulationProcessedSuccess(successes += 1);
//        taskRepository.save(subtask.getTask());
//    }
//
//    public void updateTaskFail(Subtask subtask) {
//        int fails = subtask.getTask().getPopulationProcessedFailed();
//        subtask.getTask().setPopulationProcessedFailed(fails += 1);
//        taskRepository.save(subtask.getTask());
//    }
//
//    public void updateTaskDone(List<Subtask> subtasks) {
//        Task task = subtasks.get(0).getTask();
//        task.setStatus(task.getPopulationProcessedFailed().equals(task.getPopulationSize()) ?
//                Status.FAILED : Status.DONE);
//        task.setFinishDate(new Date());
//        taskRepository.save(task);
//    }

    private void updateSubtaskSuccess(Subtask subtask) {
        // subtask.setReport(report); //TODO: org.springframework.dao.InvalidDataAccessApiUsageException: org.hibernate.TransientPropertyValueException: object references an unsaved transient instance - save the transient instance before flushing : com.demo.plagiarismdetect.model.domain.Subtask.report -> com.demo.plagiarismdetect.model.domain.Report; nested exception is java.lang.IllegalStateException: org.hibernate.TransientPropertyValueException: object references an unsaved transient instance - save the transient instance before flushing : com.demo.plagiarismdetect.model.domain.Subtask.report -> com.demo.plagiarismdetect.model.domain.Report
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
package polsl.plagiarismdetect.demo.service.batch.job.provider;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import polsl.plagiarismdetect.demo.model.domain.*;
import polsl.plagiarismdetect.demo.model.repository.FileRepository;
import polsl.plagiarismdetect.demo.model.repository.SubtaskRepository;
import polsl.plagiarismdetect.demo.model.repository.TaskParameterRepository;
import polsl.plagiarismdetect.demo.model.repository.TaskRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
@Log
public class JobProvider {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private final TaskRepository taskRepository;
    private final FileRepository fileRepository;
    private final SubtaskRepository subtaskRepository;

    private final TaskParameterRepository taskParameterRepository;

    /* setup population (subtask) */
    @Scheduled(cron = "${app.schedule.job-provider}")
    public void createPopulation() {
        /* if the new job is identified then the subtask are created */
        log.info("JobProvider.createPopulation() started");
        List<Task> tasks = taskRepository.findAllByStatusEquals(Status.TODO);
        log.info("JobProvider.createPopulation() pending tasks: " + tasks.size());
        tasks.forEach(this::createTaskParameters);
        tasks.forEach(this::createSubtasks);
        log.info("JobProvider.createPopulation() finished");

    }


    private void createTaskParameters(Task task) {
        task.setStatus(Status.IN_PROGRESS);
        List<File> files = fileRepository.findAllByIdNotIn(task.getSource().getId());
        //AtomicReference<File> tmpFile = new AtomicReference<>(new File());
        AtomicReference<Integer> previousTaskParameter = new AtomicReference<>(0);
        //AtomicReference<Integer> it = new AtomicReference<>(0);

        files.forEach(file -> {
            taskParameterRepository.save(TaskParameter.builder()
                    .antecedent(previousTaskParameter.get())
                    .repeat_number(2)
                    .condition(false)
                    .task(task)
                    .target(file)
                    .build());
            List<TaskParameter> taskParameterList = taskParameterRepository.findAll();
            taskParameterList.forEach(taskParam -> {
                if(taskParam.getTarget().equals(file) && task.getId().equals(taskParam.getTask().getId()))
                    previousTaskParameter.set(taskParam.getId());
            });
        });
        task.setPopulationSize(files.size());
        task.setPopulationProcessedSuccess(0);
        task.setPopulationProcessedFailed(0);
        taskRepository.save(task);
    }

    private void createSubtasks(Task task){
        List<TaskParameter> taskParameterList = taskParameterRepository.findAll();
        AtomicReference<List<TaskParameter>> taskParametersForPresentTask = new AtomicReference<>(new ArrayList<>());
        taskParameterList.forEach(taskParam -> {
            if(task.getId().equals(taskParam.getTask().getId())){
                taskParametersForPresentTask.get().add(taskParam);
            }
        });
        taskParametersForPresentTask.get().forEach(taskParamForPresentTask -> {
            subtaskRepository.save(Subtask.builder()
                    .creationDate(new Date())
                    .numberOfAttempts(0)
                    .taskParameter(taskParamForPresentTask)
                    .status(Status.TODO)
                    .source(task.getSource())
                    .target(taskParamForPresentTask.getTarget())
                    .build());
        });


    }

}
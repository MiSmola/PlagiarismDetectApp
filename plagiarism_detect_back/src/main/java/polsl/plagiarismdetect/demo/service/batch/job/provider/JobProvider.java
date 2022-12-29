package polsl.plagiarismdetect.demo.service.batch.job.provider;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import polsl.plagiarismdetect.demo.model.domain.File;
import polsl.plagiarismdetect.demo.model.domain.Status;
import polsl.plagiarismdetect.demo.model.domain.Subtask;
import polsl.plagiarismdetect.demo.model.domain.Task;
import polsl.plagiarismdetect.demo.model.repository.FileRepository;
import polsl.plagiarismdetect.demo.model.repository.SubtaskRepository;
import polsl.plagiarismdetect.demo.model.repository.TaskRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log
public class JobProvider {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private final TaskRepository taskRepository;
    private final FileRepository fileRepository;
    private final SubtaskRepository subtaskRepository;

    /* setup population (subtask) */
    @Scheduled(cron = "${app.schedule.job-provider}")
    public void createPopulation() {
        /* if the new job is identified then the subtask are created */
        log.info("JobProvider.createPopulation() started");
        List<Task> tasks = taskRepository.findAllByStatusEquals(Status.TODO);
        log.info("JobProvider.createPopulation() pending tasks: " + tasks.size());
        tasks.forEach(this::createSubTasks);
        log.info("JobProvider.createPopulation() finished");

    }

    private void createSubTasks(Task task) {
        task.setStatus(Status.IN_PROGRESS);

        //FIXME: check if the query is working
//        List<File> files = fileRepository.findAllByIdNotIn(task.getSource());
//        files.forEach(file -> {
//            subtaskRepository.save(Subtask.builder()
//                    .creationDate(new Date())
//                    .source(task.getSource())
//                    .target(file.getId())
//                    .task(task)
//                    .status(Status.TODO)
//                    .build());
//        });

//        task.setPopulationSize(files.size());
//        task.setPopulationProcessedSuccess(0);
//        task.setPopulationProcessedFailed(0);
//        taskRepository.save(task);
    }
}
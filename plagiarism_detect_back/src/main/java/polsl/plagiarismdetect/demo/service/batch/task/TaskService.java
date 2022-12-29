package polsl.plagiarismdetect.demo.service.batch.task;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import polsl.plagiarismdetect.demo.model.domain.Status;
import polsl.plagiarismdetect.demo.model.domain.Task;
import polsl.plagiarismdetect.demo.model.repository.FileRepository;
import polsl.plagiarismdetect.demo.model.repository.TaskRepository;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final FileRepository fileRepository;
    private final TaskRepository taskRepository;

    public Task createTask(Integer idFileSource) {
        //TODO: Handle exception in the aspects
        fileRepository.findById(idFileSource).orElseThrow(() -> new RuntimeException("File with given id: " + idFileSource + " not exists"));
//        return taskRepository.save(Task.builder().creationDate(new Date())
//                .status(Status.TODO)
//                .source(idFileSource)
//                .build());
        return null; //tmp
    }
}

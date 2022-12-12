package polsl.plagiarismdetect.demo.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import polsl.plagiarismdetect.demo.controller.TaskControllerApi;
import polsl.plagiarismdetect.demo.service.batch.task.TaskService;

@RestController
@RequiredArgsConstructor
public class TaskController implements TaskControllerApi {
    private final TaskService taskService;

    @Override
    public ResponseEntity scheduleTask(Integer idFileSource) {
        return ResponseEntity.ok(taskService.createTask(idFileSource));
    }
}

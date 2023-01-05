package polsl.plagiarismdetect.demo.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import polsl.plagiarismdetect.demo.controller.TaskControllerApi;
import polsl.plagiarismdetect.demo.service.batch.task.TaskService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class TaskController implements TaskControllerApi {
    private final TaskService taskService;

    @Override
    public ResponseEntity scheduleTask(MultipartFile fileSource, String userEmail) throws IOException {
        return ResponseEntity.ok(taskService.createTask(fileSource, userEmail));
    }
}

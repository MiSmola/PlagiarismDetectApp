package polsl.plagiarismdetect.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping(value = "/api/task", produces = MediaType.APPLICATION_JSON_VALUE)
public interface TaskControllerApi {
    @PostMapping
    ResponseEntity scheduleTask(@RequestBody MultipartFile fileSource,
                                @RequestParam String userEmail) throws IOException;

}

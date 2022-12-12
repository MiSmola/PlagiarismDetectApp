package polsl.plagiarismdetect.demo.controller.base;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface FileControllerApi {
    @GetMapping
    ResponseEntity findByLocalPath(@RequestParam String localPath);
}

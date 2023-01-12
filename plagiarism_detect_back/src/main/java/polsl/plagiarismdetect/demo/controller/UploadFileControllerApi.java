package polsl.plagiarismdetect.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import polsl.plagiarismdetect.demo.controller.base.FileControllerApi;

@RequestMapping(value = "/api/file", produces = MediaType.APPLICATION_JSON_VALUE)
public interface UploadFileControllerApi extends FileControllerApi {
    @PostMapping("/db-upload")
    ResponseEntity upload(@RequestParam("file") MultipartFile file);
}


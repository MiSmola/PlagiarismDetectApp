package polsl.plagiarismdetect.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/api/report", produces = MediaType.APPLICATION_JSON_VALUE)
public interface ReportControllerApi {
    @GetMapping
    ResponseEntity findAll();
}
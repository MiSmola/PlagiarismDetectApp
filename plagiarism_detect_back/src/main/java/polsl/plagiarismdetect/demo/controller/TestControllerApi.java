package polsl.plagiarismdetect.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import polsl.plagiarismdetect.demo.model.domain.demo.Test;

@RequestMapping(value = "/api/db-test", produces = MediaType.APPLICATION_JSON_VALUE)
public interface TestControllerApi {
    @GetMapping
    ResponseEntity testGetAll();

    @GetMapping("/find-by-id/{id}")
    ResponseEntity testFindById(@PathVariable Integer id);

    @GetMapping("/find-by-id-name")
    ResponseEntity testFindByIdAndName(@RequestParam Integer id, @RequestParam String name);

    @PostMapping
    ResponseEntity save(@RequestBody Test test);
}


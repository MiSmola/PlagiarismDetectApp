package polsl.plagiarismdetect.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import polsl.plagiarismdetect.demo.model.domain.demo.Test;

@RequestMapping(value = "/api/test", produces = MediaType.APPLICATION_JSON_VALUE)
public interface TestControllerApi {
    @GetMapping
    ResponseEntity test();

//    @PostMapping("/levenshteinV1")
//    ResponseEntity levenshtein(@RequestBody Levenshtein levenshtein) throws IOException;

    @GetMapping("/findV1/{id}")
    ResponseEntity findV1(@PathVariable Integer id);

    @GetMapping("/findV2")
    ResponseEntity findV2(@RequestParam Integer id, @RequestParam String name);

    @PostMapping
    ResponseEntity save(@RequestBody Test test);
}


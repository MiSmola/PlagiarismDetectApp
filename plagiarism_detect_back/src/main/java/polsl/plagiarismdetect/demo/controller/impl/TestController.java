package polsl.plagiarismdetect.demo.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import polsl.plagiarismdetect.demo.controller.TestControllerApi;
import polsl.plagiarismdetect.demo.model.domain.demo.Test;
import polsl.plagiarismdetect.demo.service.TestService;

@RestController
@RequiredArgsConstructor
@Log
public class TestController implements TestControllerApi {
    private final TestService testService;

    @Override
    public ResponseEntity testGetAll() {
        log.info("test");
        return ResponseEntity.ok(testService.findAll());
    }

    @Override
    public ResponseEntity testFindById(Integer id) {
        log.info("findV1/" + id);
        return ResponseEntity.ok(testService.findById(id));
    }

    @Override
    public ResponseEntity testFindByIdAndName(Integer id, String name) {
        log.info("findV2?id=" + id + "&name=" + name);
        return ResponseEntity.ok(testService.findAllByIdTestAndNameLike(id, name));
    }

    @Override
    public ResponseEntity save(Test test) {
        log.info("save " + test);
        return ResponseEntity.ok(testService.save(test));
    }
}


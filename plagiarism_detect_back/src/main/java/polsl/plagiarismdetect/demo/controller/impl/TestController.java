package polsl.plagiarismdetect.demo.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import polsl.plagiarismdetect.demo.controller.TestControllerApi;
import polsl.plagiarismdetect.demo.model.domain.demo.Levenshtein;
import polsl.plagiarismdetect.demo.model.domain.demo.Test;
import polsl.plagiarismdetect.demo.service.TestService;
import polsl.plagiarismdetect.demo.service.algorithmic.levenshtein.LevenshteinAlgorithmService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Log
public class TestController implements TestControllerApi {
    private final TestService testService;
    private final LevenshteinAlgorithmService levenshteinAlgorithmService;

    @Override
    public ResponseEntity test() {
        log.info("test");
        return ResponseEntity.ok(testService.findAll());
    }

    @Override
    public ResponseEntity levenshtein(Levenshtein levenshtein) throws IOException {
        return ResponseEntity.ok(levenshteinAlgorithmService.levenshtein(
                levenshtein.getSource1(), levenshtein.getSource2(), LevenshteinAlgorithmService.IMPL_TYPE.PYTHON));
    }

    @Override
    public ResponseEntity findV1(Integer id) {
        log.info("findV1/" + id);
        return ResponseEntity.ok(testService.findById(id));
    }

    @Override
    public ResponseEntity findV2(Integer id, String name) {
        log.info("findV2?id=" + id + "&name=" + name);
        return ResponseEntity.ok(testService.findAllByIdTestAndNameLike(id, name));
    }

    @Override
    public ResponseEntity save(Test test) {
        log.info("save " + test);
        return ResponseEntity.ok(testService.save(test));
    }
}


package polsl.plagiarismdetect.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import polsl.plagiarismdetect.demo.model.domain.demo.Test;
import polsl.plagiarismdetect.demo.model.repository.TestRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestRepository testRepository;

    public List<Test> findAll() {
        return testRepository.findAll();
    }

    public List<Test> findById(Integer id) {
        return testRepository.findAllById(List.of(id));
    }

    public List<Test> findAllByIdTestAndNameLike(Integer id, String name) {
        return testRepository.findAllByIdTestAndNameLike(id, name);
    }

    public Test save(Test test) {
        return testRepository.save(test);
    }
}


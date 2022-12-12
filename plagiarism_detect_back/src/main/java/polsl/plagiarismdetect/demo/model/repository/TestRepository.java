package polsl.plagiarismdetect.demo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import polsl.plagiarismdetect.demo.model.domain.demo.Test;

import java.util.List;

public interface TestRepository extends JpaRepository<Test, Integer> {
    List<Test> findAllByIdTestAndNameLike(Integer id, String name);
}

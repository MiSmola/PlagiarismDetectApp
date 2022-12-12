package polsl.plagiarismdetect.demo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import polsl.plagiarismdetect.demo.model.domain.Status;
import polsl.plagiarismdetect.demo.model.domain.Subtask;

import java.util.List;

public interface SubtaskRepository extends JpaRepository<Subtask, Integer> {
    List<Subtask> findAllByStatusEquals(Status status);
}

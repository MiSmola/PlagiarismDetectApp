package polsl.plagiarismdetect.demo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import polsl.plagiarismdetect.demo.model.domain.Status;
import polsl.plagiarismdetect.demo.model.domain.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findAllByStatusEquals(Status status);
}
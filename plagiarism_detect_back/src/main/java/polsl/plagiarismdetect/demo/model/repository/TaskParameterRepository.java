package polsl.plagiarismdetect.demo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import polsl.plagiarismdetect.demo.model.domain.TaskParameter;

public interface TaskParameterRepository extends JpaRepository<Integer, TaskParameter> {
}

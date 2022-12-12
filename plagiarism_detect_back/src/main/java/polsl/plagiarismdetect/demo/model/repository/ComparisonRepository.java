package polsl.plagiarismdetect.demo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import polsl.plagiarismdetect.demo.model.domain.Comparison;

public interface ComparisonRepository extends JpaRepository<Comparison, Integer> {
}

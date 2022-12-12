package polsl.plagiarismdetect.demo.model.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import polsl.plagiarismdetect.demo.model.domain.Report;

public interface ReportRepository extends JpaRepository<Report, Integer> {
}

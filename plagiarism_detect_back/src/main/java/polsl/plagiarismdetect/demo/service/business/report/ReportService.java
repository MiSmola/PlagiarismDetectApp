package polsl.plagiarismdetect.demo.service.business.report;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import polsl.plagiarismdetect.demo.model.domain.Report;
import polsl.plagiarismdetect.demo.model.repository.ReportRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;

    public List<Report> findAll() {
        return reportRepository.findAll();
    }
}

package polsl.plagiarismdetect.demo.service.business.report;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import polsl.plagiarismdetect.demo.infrastructure.dto.ComparisonDto;
import polsl.plagiarismdetect.demo.infrastructure.dto.ReportDto;
import polsl.plagiarismdetect.demo.model.domain.Comparison;
import polsl.plagiarismdetect.demo.model.domain.Report;
import polsl.plagiarismdetect.demo.model.repository.ReportRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;

    public List<ReportDto> findAll() {
        List<Report> reports = reportRepository.findAll();
        List<ReportDto> reportDtos = new ArrayList();
        for (Report report : reports) {
            List<ComparisonDto> comparisonDtos = new ArrayList<>();
            for (Comparison comparison : report.getComparisons()) {
                comparisonDtos.add(ComparisonDto.builder()
                                .comparisonCreationDate(comparison.getCreationDate())
                        .levenshteinCoefficient(comparison.getLevenshteinCoefficient())
                        .levenshteinCoefficientPercentage(String.valueOf(Double.parseDouble(comparison.getLevenshteinCoefficient())/comparison.getSource().getSize()))
                        .matcherCoefficient(comparison.getMatcherCoefficient())
                        .matcherCoefficientPercentage(String.valueOf(Double.parseDouble(comparison.getMatcherCoefficient())/comparison.getTarget().getSize()))
                                .sourceFileName(comparison.getSource().getLocalPath())
                                .sourceFileCreationDate(comparison.getSource().getCreationDate())
                                .targetFileName(comparison.getTarget().getLocalPath())
                                .targetFileCreationDate(comparison.getCreationDate())
                        .build());
            }
            reportDtos.add(ReportDto.builder().creationDate(report.getCreationDate())
                    .comparisonDtoList(comparisonDtos)
                    .build());
        }
//        return reportRepository.findAll();
        return reportDtos;
    }

}

package polsl.plagiarismdetect.demo.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import polsl.plagiarismdetect.demo.controller.ReportControllerApi;
import polsl.plagiarismdetect.demo.service.business.report.ReportService;

@RestController
@RequiredArgsConstructor
public class ReportController implements ReportControllerApi {
    private final ReportService reportService;

    @Override
    public ResponseEntity findAll() {
        return ResponseEntity.ok(reportService.findAll());
    }
}

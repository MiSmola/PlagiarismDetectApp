package polsl.plagiarismdetect.demo.controller.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import polsl.plagiarismdetect.demo.controller.SynchronousLocalFileControllerApi;
import polsl.plagiarismdetect.demo.controller.base.impl.FileController;
import polsl.plagiarismdetect.demo.controller.dto.ResponseDto;
import polsl.plagiarismdetect.demo.service.business.file.FileService;
import polsl.plagiarismdetect.demo.service.business.file.SynchronousLocalFileService;

@RestController
public class SynchronousLocalFileController extends FileController implements SynchronousLocalFileControllerApi {
    private final SynchronousLocalFileService synchronousLocalFileService;

    public SynchronousLocalFileController(FileService fileService, SynchronousLocalFileService synchronousLocalFileService) {
        super(fileService);
        this.synchronousLocalFileService = synchronousLocalFileService;
    }

    @Override
    public ResponseEntity upload(String path) {
        synchronousLocalFileService.upload(path);
        return ResponseEntity.ok(ResponseDto.builder().message(path).build());
    }
}
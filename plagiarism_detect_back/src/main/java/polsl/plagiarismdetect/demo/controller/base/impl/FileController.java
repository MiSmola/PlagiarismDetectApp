package polsl.plagiarismdetect.demo.controller.base.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import polsl.plagiarismdetect.demo.controller.base.FileControllerApi;
import polsl.plagiarismdetect.demo.service.business.file.FileService;

@RequiredArgsConstructor
public class FileController implements FileControllerApi {

    private final FileService fileService;

    @Override
    public ResponseEntity findByLocalPath(String localPath) {
        return ResponseEntity.ok(fileService.findByLocalPath(localPath));
    }
}

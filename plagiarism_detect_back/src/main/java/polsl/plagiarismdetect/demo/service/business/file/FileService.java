package polsl.plagiarismdetect.demo.service.business.file;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import polsl.plagiarismdetect.demo.model.domain.File;
import polsl.plagiarismdetect.demo.model.repository.FileRepository;


@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;

    public File findByLocalPath(String localPath) {
        return fileRepository.findByLocalPath(localPath);
    }
}
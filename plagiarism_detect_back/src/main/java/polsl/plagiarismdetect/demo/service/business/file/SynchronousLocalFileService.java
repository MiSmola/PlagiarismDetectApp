package polsl.plagiarismdetect.demo.service.business.file;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import polsl.plagiarismdetect.demo.model.domain.File;
import polsl.plagiarismdetect.demo.model.domain.Users;
import polsl.plagiarismdetect.demo.model.repository.FileRepository;
import polsl.plagiarismdetect.demo.model.repository.UserRepository;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log
public class SynchronousLocalFileService {

    private final FileRepository fileRepository;
    private final UserRepository userRepository;

    public File upload(MultipartFile file) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        try {
            List<Users> user = userRepository.findAllById(List.of(1));
            return fileRepository.save(File.builder()
                            .creationDate(new Date())
                            .extension(extension)
                            .file(file.getBytes())
                            .localPath(file.getOriginalFilename())
                            .size(file.getSize())
                            .idUsers(user.get(0))
                            .build());

            //TODO fix user
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

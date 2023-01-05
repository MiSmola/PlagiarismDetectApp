package polsl.plagiarismdetect.demo.service.business.file;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${app.binary.save}")
    private boolean binarySave;

    private final FileRepository fileRepository;
    private final UserRepository userRepository;

    public File upload(MultipartFile file) {
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
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

    //TODO: add filter
//    public void upload(String path) {
//        Path givenPath = Paths.get(path);
//        try {
//            if (Files.isDirectory(givenPath)) {
//                try (Stream<Path> paths = Files.walk(givenPath)) {
//                    paths.filter(Files::isRegularFile).forEach(file -> {
//                                try {
//                                    fileRepository.save(buildFile(file));
//                                } catch (IOException e) {
//                                    log.severe(e.getMessage());
//                                }
//                            }
//                    );
//                }
//            } else if (Files.isRegularFile(givenPath))
//                fileRepository.save(buildFile(givenPath));
//        } catch (Exception e) {
//            log.severe(e.getMessage());
//        }
//    }

//    private File buildFile(Path path) throws IOException {
//        return File.builder().creationDate(new Date())
//                .extension(Utils.getFileExtension(path))
//                .file(binarySave ? Files.readAllBytes(path) : null)
//                .localPath(path.toAbsolutePath().toString())
//                .size(path.toFile().length())
//                .build();
//    }
}

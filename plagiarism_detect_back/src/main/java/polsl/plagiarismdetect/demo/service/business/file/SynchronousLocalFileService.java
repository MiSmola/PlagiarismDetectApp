package polsl.plagiarismdetect.demo.service.business.file;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import polsl.plagiarismdetect.demo.model.domain.File;
import polsl.plagiarismdetect.demo.model.repository.FileRepository;
import polsl.plagiarismdetect.demo.service.utils.Utils;
import org.apache.commons.io.FilenameUtils;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Log
public class SynchronousLocalFileService {
    @Value("${app.binary.save}")
    private boolean binarySave;

    private final FileRepository fileRepository;

    public File upload(MultipartFile file) {
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        try {
            return fileRepository.save(File.builder()
                            .creationDate(new Date())
                            .extension(extension)
                            .file(file.getBytes())
                            .localPath(file.getOriginalFilename())
                            .size(file.getSize())
                          //.idUsers(1)
                            .build());
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

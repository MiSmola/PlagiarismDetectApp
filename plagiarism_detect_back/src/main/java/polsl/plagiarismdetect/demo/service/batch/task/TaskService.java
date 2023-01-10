package polsl.plagiarismdetect.demo.service.batch.task;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import polsl.plagiarismdetect.demo.model.domain.File;
import polsl.plagiarismdetect.demo.model.domain.Status;
import polsl.plagiarismdetect.demo.model.domain.Task;
import polsl.plagiarismdetect.demo.model.domain.Users;
import polsl.plagiarismdetect.demo.model.repository.FileRepository;
import polsl.plagiarismdetect.demo.model.repository.TaskRepository;
import polsl.plagiarismdetect.demo.model.repository.UserRepository;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final FileRepository fileRepository;
    private final TaskRepository taskRepository;

    private final UserRepository userRepository;

    public Task createTask(MultipartFile fileSource, String userEmail) throws IOException {
        List<Users> allUsers = userRepository.findAll();
        AtomicReference<Users> presentUser = new AtomicReference<>(new Users());
        allUsers.forEach(user -> {
            if(user.getEmail().equals(userEmail)){
                presentUser.set(user);
            }
        });

            fileRepository.save(File.builder()
                    .creationDate(new Date())
                    .file(fileSource.getBytes())
                    .localPath(fileSource.getOriginalFilename())
                    .extension(FilenameUtils.getExtension(fileSource.getOriginalFilename()))
                    .size(fileSource.getSize())
                    .idUsers(presentUser.get())
                    .build());


        List<File> allFiles = fileRepository.findAll();
        AtomicReference<Integer> retrievedFileId = new AtomicReference<>(0);
        allFiles.forEach(file -> {
            if(file.getLocalPath().equals(fileSource.getOriginalFilename()) && file.getIdUsers().getEmail().equals(userEmail))
                retrievedFileId.set(file.getId());
        });

        Integer tempFileId = retrievedFileId.get();

        //TODO: Handle exception in the aspects
        File sourceFile = fileRepository.findById(tempFileId)
                .orElseThrow(() -> new RuntimeException("File with given id: " + fileSource.getName() + " not exists"));
        return taskRepository.save(Task.builder()
                .creationDate(new Date())
                .status(Status.TODO)
                .users(presentUser.get())
                .source(sourceFile)
                .build());
        //add user ID



    }

//    private Integer retrieveFileIdByParams(String originalFileName, String userEmail){
//        //Integer retrievedUserId = 0;
//        List<File> allFiles = fileRepository.findAll();
//        AtomicReference<Integer> retrievedUserId = new AtomicReference<>(0);
//        allFiles.forEach(file -> {
//            if(file.getLocalPath() == originalFileName && file.getIdUsers().getEmail() == userEmail)
//                retrievedUserId.set(file.getIdUsers().getId());
//        });
//        return retrievedUserId.get();
//    }
}

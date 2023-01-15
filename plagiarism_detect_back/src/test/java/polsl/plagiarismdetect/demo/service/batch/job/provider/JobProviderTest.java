package polsl.plagiarismdetect.demo.service.batch.job.provider;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import polsl.plagiarismdetect.demo.model.domain.*;
import polsl.plagiarismdetect.demo.model.repository.FileRepository;
import polsl.plagiarismdetect.demo.model.repository.SubtaskRepository;
import polsl.plagiarismdetect.demo.model.repository.TaskParameterRepository;
import polsl.plagiarismdetect.demo.model.repository.TaskRepository;

import java.util.Date;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class JobProviderTest {
    @MockBean
    private TaskRepository taskRepository;
    @Mock
    private FileRepository fileRepository;
    @Mock
    private SubtaskRepository subtaskRepository;
    @Mock
    private TaskParameterRepository taskParameterRepository;

    private JobProvider jobProvider;

    @BeforeEach
    void setUp() {
        jobProvider = new JobProvider(taskRepository, fileRepository, subtaskRepository, taskParameterRepository);
    }

    @Before
    public void setUp1(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void givenTask_whenCreated_thenScheduled() {
//        File mockFile = File.builder().id(3).file("ABC".getBytes()).creationDate(new Date()).localPath("C:/file.txt").extension("txt").idUsers(Users.builder().id(1).name("Jan").surname("Kowalski").email("example").build()).build();
//        fileRepository.save(mockFile);
        Task task = Task.builder().id(1)
                .creationDate(new Date())
                .populationProcessedFailed(0)
                .populationProcessedSuccess(0)
                        .populationSize(1)
                                .status(Status.TODO)
                                        .report(Report.builder().creationDate(new Date()).title("test").build())
                                                .source(File.builder().creationDate(new Date()).localPath("test.txt").extension(".txt").build())
                                                        .users(Users.builder().id(1).name("Jan").surname("Kowalski").email("example").build()).build();


        jobProvider.createTaskParameters(task);
        List<Task> task1 = taskRepository.findAll();
        System.out.println(task1);
    }
}
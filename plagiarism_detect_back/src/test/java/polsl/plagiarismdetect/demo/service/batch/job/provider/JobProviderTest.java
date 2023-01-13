package polsl.plagiarismdetect.demo.service.batch.job.provider;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import polsl.plagiarismdetect.demo.model.domain.*;
import polsl.plagiarismdetect.demo.model.repository.FileRepository;
import polsl.plagiarismdetect.demo.model.repository.SubtaskRepository;
import polsl.plagiarismdetect.demo.model.repository.TaskParameterRepository;
import polsl.plagiarismdetect.demo.model.repository.TaskRepository;

import java.util.Collections;
import java.util.Date;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class JobProviderTest {
    @Mock
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

    @Test
    void givenTask_whenCreated_thenScheduled() {
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
        verify(taskRepository).findAllById(Collections.singleton(task.getId()));
    }
}
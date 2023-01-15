package polsl.plagiarismdetect.demo.service.batch.fileResolve;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import polsl.plagiarismdetect.demo.model.domain.File;
import polsl.plagiarismdetect.demo.model.domain.Users;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;
@RunWith(SpringRunner.class)
class FileResolveServiceTest {


    private final FileResolveService fileResolveService = new FileResolveService();

    @Test
    void givenByteArray_whenInvokedTransform_createValidFile() throws IOException {
        String fileContent = "Vestibulum vel rutrum ante.";
        File dbFile = File.builder().id(3).file(fileContent.getBytes()).creationDate(new Date()).localPath("C:/file.txt").extension("txt").idUsers(Users.builder().id(1).name("Jan").surname("Kowalski").email("example").build()).build();

        String absolutePath = fileResolveService.transform("testFile.txt", dbFile);
        assertNotNull(FileUtils.getFile(absolutePath));

        Assert.assertArrayEquals(Files.readAllBytes(Path.of(absolutePath)), fileContent.getBytes());

    }
}
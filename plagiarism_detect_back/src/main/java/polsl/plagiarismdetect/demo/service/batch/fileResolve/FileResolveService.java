package polsl.plagiarismdetect.demo.service.batch.fileResolve;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class FileResolveService {

    public String transform(String filename, polsl.plagiarismdetect.demo.model.domain.File dbFile) {
        try {
            File file = new File(System.getProperty("java.io.tmpdir") + filename);
            FileUtils.writeByteArrayToFile(file, dbFile.getFile());
            if (file.exists())
                return file.getAbsolutePath().replace("\\", "/");
            else
                throw new RuntimeException("Could not read the file!");

        } catch (IOException e) {
            throw new RuntimeException("Could not convert binary file to file!");
        }
    }

}

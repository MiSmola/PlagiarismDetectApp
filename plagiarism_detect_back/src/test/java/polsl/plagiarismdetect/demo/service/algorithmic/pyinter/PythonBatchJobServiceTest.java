package polsl.plagiarismdetect.demo.service.algorithmic.pyinter;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
class PythonBatchJobServiceTest {
    private final PythonBatchJobService pythonBatchJobService = new PythonBatchJobService();

    @Test
    void givenParams_whenExecute_thenReceiveResults() throws IOException {
        String fileContent1 = "Vestibulum vel rutrum ante.";
        String fileContent2 = "Nunc imperdiet sapien non nulla hendrerit consequat.";
        File file1 = new File(System.getProperty("java.io.tmpdir") + "test1.txt");
        File file2 = new File(System.getProperty("java.io.tmpdir") + "test2.txt");
        FileUtils.write(file1, fileContent1);
        FileUtils.write(file2, fileContent2);
        List<String> params = new ArrayList<>();
        if (file1.exists() && file2.exists()) {
            params.add(file1.getAbsolutePath().replace("\\", "/"));
            params.add(file2.getAbsolutePath().replace("\\", "/"));
        }

        List<String> testCosine = pythonBatchJobService.performScript("C:/Users/djnic/AppData/Local/Programs/Python/Python310/python.exe",
                "C:/Users/djnic/git/plagiarismdetectapp/plagiarism_detect_python/check_cosine.py",
                params.get(0), params.get(1));
        List<String> testLevenshtein = pythonBatchJobService.performScript("C:/Users/djnic/AppData/Local/Programs/Python/Python310/python.exe",
                "C:/Users/djnic/git/plagiarismdetectapp/plagiarism_detect_python/check_levenshtein.py",
                params.get(0), params.get(1));
        List<String> testJaroWinkler = pythonBatchJobService.performScript("C:/Users/djnic/AppData/Local/Programs/Python/Python310/python.exe",
                "C:/Users/djnic/git/plagiarismdetectapp/plagiarism_detect_python/check_jarowinkler.py",
                params.get(0), params.get(1));

        assertEquals(Double.parseDouble(testCosine.get(0)), 0.0);
        assertEquals(Integer.parseInt(testLevenshtein.get(0)), 40);
        assertEquals(Double.parseDouble(testJaroWinkler.get(0)), 0.5584520417853751);
    }
}
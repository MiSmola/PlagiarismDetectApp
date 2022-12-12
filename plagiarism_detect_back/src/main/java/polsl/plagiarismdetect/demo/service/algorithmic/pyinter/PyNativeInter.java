package polsl.plagiarismdetect.demo.service.algorithmic.pyinter;

import java.io.IOException;
import java.util.List;

public interface PyNativeInter {
    List<String> performScript(String python, String script, String... params) throws IOException;
}

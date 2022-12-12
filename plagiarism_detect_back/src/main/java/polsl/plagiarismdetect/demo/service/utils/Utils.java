package polsl.plagiarismdetect.demo.service.utils;

import lombok.experimental.UtilityClass;

import java.nio.file.Path;

@UtilityClass
public class Utils {
    public String getFileExtension(Path path) {
        String name = path.getFileName().toString();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1)
            return "";
        return name.substring(lastIndexOf);
    }
}

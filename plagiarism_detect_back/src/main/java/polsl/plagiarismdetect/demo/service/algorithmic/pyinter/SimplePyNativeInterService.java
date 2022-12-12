package polsl.plagiarismdetect.demo.service.algorithmic.pyinter;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("SimplePyNativeInterService")
@Log
public class SimplePyNativeInterService implements PyNativeInter {
    public List<String> performScript(String python, String script, String... params) throws IOException {
        return executeScript(python, script, params);
    }

    private List<String> setupCommands(String python, String script, String... params) {
        log.info(String.format("Process setup with: %s %s", python, script));
        log.info(String.format("Params: %s", Arrays.asList(params)));
        List<String> command = new ArrayList<>(2 + params.length);
        command.add(python);
        command.add(script);
        command.addAll(Arrays.asList(params));
        return command;
    }

    private List<String> executeScript(String python, String script, String... params) throws IOException {
        ProcessBuilder pb = new ProcessBuilder(setupCommands(python, script, params));
        Process p = pb.start();
        BufferedReader bfr = new BufferedReader(new InputStreamReader(p.getInputStream()));

        log.info("Process started...");
        List<String> results = new ArrayList<>();
        String line;
        while ((line = bfr.readLine()) != null) {
            results.add(line);
            log.info(String.format("Py output: %s", line));
        }
        log.info(String.format("Process finished with results of: %s...", results));
        return results;
    }
}


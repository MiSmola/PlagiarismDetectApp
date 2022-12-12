package polsl.plagiarismdetect.demo.service.algorithmic.levenshtein;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import polsl.plagiarismdetect.demo.model.domain.demo.Result;
import polsl.plagiarismdetect.demo.service.algorithmic.pyinter.PyNativeInter;

import java.io.IOException;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class LevenshteinAlgorithmService {
    @Value("${app.python.binpath}")
    private String python;
    @Value("${app.python.script.leven}")
    private String scriptLeven;
    @Value("${app.python.script.matcher}")
    private String scriptMatcher;

    public enum IMPL_TYPE {JAVA, PYTHON}

    private final PyNativeInter pyNativeInter;

    public Result levenshtein(String source1, String source2, IMPL_TYPE implType) throws IOException {
        Result res = null;
        switch (implType) {
            case JAVA -> res = levenshteinJava(source1, source2);
            case PYTHON -> res = levenshteinPython(source1, source2);
        }
        return res;
    }

    public Result levenshteinJava(String source1, String source2) {
        if (source1.isEmpty())
            return Result.builder().value(source1.length()).build();
        if (source2.isEmpty())
            return Result.builder().value(source2.length()).build();
        Integer substitutions = levenshteinJava(source1.substring(1), source2.substring(1)).getValue()
                + calculateCost(source1.charAt(0), source2.charAt(0)),
                insertions = levenshteinJava(source1, source2.substring(1)).getValue() + 1,
                deletion = levenshteinJava(source1.substring(1), source2).getValue() + 1;
        return Result.builder().value(min(substitutions, insertions, deletion)).build();
    }

    public Result levenshteinPython(String source1, String source2) throws IOException {
        //TODO: "\"" introduced to handle multiply lines file
        return Result.builder().value(Integer.parseInt(pyNativeInter.performScript(
                python, scriptLeven, source1.replace("\n", ""), source2.replace("\n", "")).get(0))).build();
    }

    private int calculateCost(char char1, char char2) {
        return char1 == char2 ? 0 : 1;
    }

    private int min(Integer... numbers) {
        return Arrays.stream(numbers).min(Integer::compare).get();
    }
}

package polsl.plagiarismdetect.demo.model.domain.demo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Levenshtein {
    private String source1;
    private String source2;
}


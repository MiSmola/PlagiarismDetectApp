package polsl.plagiarismdetect.demo.infrastructure.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
@Builder
public class ComparisonDto {
    private Date comparisonCreationDate;
    private String levenshteinCoefficient;
    private String jaroWinklerCoefficient;
    private String cosineSimilarity;
    private String sourceFileName;
    private Date sourceFileCreationDate;
    private String targetFileName;
    private Date targetFileCreationDate;
}

package polsl.plagiarismdetect.demo.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Builder
public class ReportDto {
    public Integer id;
    public String title;
    public Date creationDate;
    public List<ComparisonDto> comparisonDtoList = new ArrayList<>();
}

package polsl.plagiarismdetect.demo.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import polsl.plagiarismdetect.demo.model.domain.Comparison;
import polsl.plagiarismdetect.demo.model.domain.File;
import polsl.plagiarismdetect.demo.model.domain.Report;
import polsl.plagiarismdetect.demo.model.repository.ReportRepository;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ReportControllerApiTest {
    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void givenReport_whenSave_thenSavedWithAllData() {
        Report report = Report.builder().id(1).title("test report").creationDate(new Date())
                .comparisons(List.of(Comparison.builder()
                        .id(1)
                        .cosineSimilarity("0.99")
                        .creationDate(new Date())
                        .jaroWinklerCoefficient("0.90")
                        .levenshteinCoefficient("22")
                        .source(File.builder().id(1).creationDate(new Date()).extension(".txt").localPath("text.txt").build())
                        .target(File.builder().id(2).creationDate(new Date()).extension(".txt").localPath("file.txt").build()).build())).build();

        assertThat(report).hasFieldOrPropertyWithValue("id", 1);
        assertThat(report).hasFieldOrPropertyWithValue("title", "test report");
    }
}
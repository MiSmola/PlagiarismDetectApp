package polsl.plagiarismdetect.demo.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "task")
@SequenceGenerator(name = "seq_task", sequenceName = "seq_task", allocationSize = 1)
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_task")
    private Integer id;

    @Column(name = "id_file_source")
    private Integer source;

    @Column(name = "population_size")
    private Integer populationSize;

    @Column(name = "population_processed_success")
    private Integer populationProcessedSuccess;

    @Column(name = "population_processed_failed")
    private Integer populationProcessedFailed;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "finish_date")
    private Date finishDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date")
    private Date creationDate;

    @Enumerated(EnumType.STRING)
    private Status status;
}

package polsl.plagiarismdetect.demo.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.print.attribute.IntegerSyntax;
import java.util.Date;
import java.util.List;

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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "finish_date")
    private Date finishDate;

    @Column(name = "population_processed_failed")
    private Integer populationProcessedFailed;

    @Column(name = "population_processed_success")
    private Integer populationProcessedSuccess;

    @Column(name = "population_size")
    private Integer populationSize;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_report")
    private Report report;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private Users users;

//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "id_file_source")
    @Column(name = "id_file_source")
    private Integer source;

}

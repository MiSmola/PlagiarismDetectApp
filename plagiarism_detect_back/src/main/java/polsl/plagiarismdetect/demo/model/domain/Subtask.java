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
@Table(name = "subtask")
@SequenceGenerator(name = "seq_subtask", sequenceName = "seq_subtask", allocationSize = 1)
public class Subtask {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_subtask")
    private Integer id;

    @Column(name = "id_file_source")
    private Integer source;

    @Column(name = "id_file_target")
    private Integer target;

    //TODO: To be removed - one report per Task
    @OneToOne
    @JoinColumn(name = "id_report")
    private Report report;

    //FIXME: change to FetchType.LAZY
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_task")
    private Task task;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "finish_date")
    private Date finishDate;

    private String remarks;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date")
    private Date creationDate;

    @Enumerated(EnumType.STRING)
    private Status status;
}

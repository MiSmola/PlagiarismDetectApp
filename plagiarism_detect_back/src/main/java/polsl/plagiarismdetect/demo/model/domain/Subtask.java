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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_file_source")
    private File source;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_file_target")
    private File target;

    //FIXME: change to FetchType.LAZY
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_task_parameter")
    private TaskParameter taskParameter;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "finish_date")
    private Date finishDate;

    @Column(name = "remark")
    private String remark;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date")
    private Date creationDate;

    @Enumerated(EnumType.STRING)
    private Status status;
}

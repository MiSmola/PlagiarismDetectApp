package polsl.plagiarismdetect.demo.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "task_parameter")
@SequenceGenerator(name = "seq_task_parameter", sequenceName = "seq_task_parameter", allocationSize = 1)
public class TaskParameter {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_task_parameter")
    private Integer id;

    @Column(name = "location")
    private String location;

    @Column(name = "antecedent")
    private Integer antecedent;

    @Column(name = "repeat_number")
    private Integer repeat_number;

    @Column(name = "condition")
    private boolean condition;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_task")
    private Task task;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_file_target")
    private File target;

}

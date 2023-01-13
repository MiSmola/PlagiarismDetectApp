package polsl.plagiarismdetect.demo.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "report")
@SequenceGenerator(name = "seq_report", sequenceName = "seq_report", allocationSize = 1)
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_report")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date")
    private Date creationDate;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER) //FIXME: Added cascade
    @JoinTable(name = "report_comparison", joinColumns = @JoinColumn(name = "id_report"),
            inverseJoinColumns = @JoinColumn(name = "id_comparison"))
    private List<Comparison> comparisons;


}
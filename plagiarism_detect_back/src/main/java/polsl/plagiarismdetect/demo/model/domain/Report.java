package polsl.plagiarismdetect.demo.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    //FIXME: change to FetchType.LAZY
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER) //FIXME: Added cascade
    @JoinTable(name = "report_comparison", joinColumns = @JoinColumn(name = "id_comparison"),
            inverseJoinColumns = @JoinColumn(name = "id_report"))
    private List<Comparison> comparisons; //TODO: check why initialization not working = new ArrayList<>();
}
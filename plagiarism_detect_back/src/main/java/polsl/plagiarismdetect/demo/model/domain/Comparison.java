package polsl.plagiarismdetect.demo.model.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "reports")
@Entity
@Table(name = "comparison")
@SequenceGenerator(name = "seq_comparison", sequenceName = "seq_comparison", allocationSize = 1)
public class Comparison {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_comparison")
    private Integer id;

    //FIXME: change to FetchType.LAZY
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_file_source")
    private File source;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_file_target")
    private File target;

    @Column(name = "levenshtein_coefficient")
    private String levenshteinCoefficient;

    @Column(name = "jaro_winkler_coefficient")
    private String jaroWinklerCoefficient;

    @Column(name = "cosine_sim")
    private String cosineSimilarity;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date")
    private Date creationDate;

    //FIXME: change to FetchType.LAZY
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "comparisons") //FIXME: Added cascade
    @JsonIgnore
    private List<Report> reports;
}

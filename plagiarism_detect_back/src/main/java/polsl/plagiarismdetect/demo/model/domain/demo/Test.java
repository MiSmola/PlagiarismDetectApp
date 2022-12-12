package polsl.plagiarismdetect.demo.model.domain.demo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "test")
@SequenceGenerator(name = "seq_test", sequenceName = "seq_test", allocationSize = 1)
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_test")
    @Column(name = "id_test")
    private Integer idTest;

    private String name;

    private String subName;
}

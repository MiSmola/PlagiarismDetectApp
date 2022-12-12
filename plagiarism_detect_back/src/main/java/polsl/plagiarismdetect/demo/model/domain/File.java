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
@Table(name = "file")
@SequenceGenerator(name = "seq_file", sequenceName = "seq_file", allocationSize = 1)
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_file")
    private Integer id;

    private byte[] file;

    @Column(name = "local_path")
    private String localPath;

    @Column(name = "extension")
    private String extension;

    private Long size;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date")
    private Date creationDate;
}

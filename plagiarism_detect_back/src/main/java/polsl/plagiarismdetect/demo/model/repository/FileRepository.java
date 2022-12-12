package polsl.plagiarismdetect.demo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import polsl.plagiarismdetect.demo.model.domain.File;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Integer> {
    List<File> findAllByIdNotIn(Integer... ids);

    File findByLocalPath(String localPath);
}


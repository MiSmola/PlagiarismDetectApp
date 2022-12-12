package polsl.plagiarismdetect.demo.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackages = "${app.postgresql.entity.scan.package}")
public class PostgreSQLDataBaseConfiguration {
}

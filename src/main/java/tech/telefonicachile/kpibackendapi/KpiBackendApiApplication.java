package tech.telefonicachile.kpibackendapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import tech.telefonicachile.kpibackendapi.config.AsyncConfig;


@SpringBootApplication
@Import(AsyncConfig.class)
public class KpiBackendApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(KpiBackendApiApplication.class, args);
	}
}


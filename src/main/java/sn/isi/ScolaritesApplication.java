package sn.isi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "sn.isi.mappings")
public class ScolaritesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScolaritesApplication.class, args);
	}

}

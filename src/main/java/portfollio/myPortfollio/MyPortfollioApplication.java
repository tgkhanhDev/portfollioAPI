package portfollio.myPortfollio;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition
public class MyPortfollioApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyPortfollioApplication.class, args);
	}

}

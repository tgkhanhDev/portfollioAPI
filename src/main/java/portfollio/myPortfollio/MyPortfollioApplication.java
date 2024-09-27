package portfollio.myPortfollio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
@OpenAPIDefinition
public class MyPortfollioApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyPortfollioApplication.class, args);
    }
}

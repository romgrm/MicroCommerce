package fr.romgrm.microcommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2 // permet de créer la doc directement et simplement. On va la chercher avec  http://localhost:9090/swagger-ui.html
// pour l'avoir en html ou http://localhost:9090/v2/api-docs pour l'avoir en Json
public class MicrocommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicrocommerceApplication.class, args);
	}

}

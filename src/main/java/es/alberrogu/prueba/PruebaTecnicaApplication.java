package es.alberrogu.prueba;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class PruebaTecnicaApplication implements CommandLineRunner {

	@Autowired
	FindProducts findProducts;

	public static void main(String[] args) {
		SpringApplication.run(PruebaTecnicaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.trace("Starting application");
		log.info("El resultado es: {}", findProducts.getAllOrdered());
	}

}

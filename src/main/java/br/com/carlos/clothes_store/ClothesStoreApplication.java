package br.com.carlos.clothes_store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"target.generated-sources.mappers"})
public class ClothesStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClothesStoreApplication.class, args);
	}

}

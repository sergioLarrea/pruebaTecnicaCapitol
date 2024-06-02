package com.capitol.pruebatecnicacapitol;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@OpenAPIDefinition(info = @Info(title = "Spaceship API", version = "1.0", description = "Documentation for Spaceship API"))
public class PruebaTecnicaCapitolApplication {

    public static void main(String[] args) {
        SpringApplication.run(PruebaTecnicaCapitolApplication.class, args);
    }

}

package com.capitol.pruebatecnicacapitol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PruebaTecnicaCapitolApplication {

    public static void main(String[] args) {
        SpringApplication.run(PruebaTecnicaCapitolApplication.class, args);
    }

}

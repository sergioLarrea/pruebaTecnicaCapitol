package com.capitol.pruebatecnicacapitol.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Aspect para añadir una línea de log cuando se solicita una nave con un ID negativo.
 */
@Aspect
@Component
public class LoggingAspect {


    /**
     * Advice que se ejecuta antes de la ejecución del método getSpaceshipById en SpaceshipService.
     * Si el ID es negativo, se añade una línea de log.
     *
     * @param id El ID de la nave espacial solicitada.
     */
    @Before("execution(* com.capitol.pruebatecnicacapitol.service.SpaceshipService.getSpaceshipById(..)) && args(id)")
    public void logBefore(Long id) {
        if (id < 0) {
            System.out.println("Request with negative ID: " + id);
        }
    }
}

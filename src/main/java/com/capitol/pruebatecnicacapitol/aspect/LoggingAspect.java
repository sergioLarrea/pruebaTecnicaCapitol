package com.capitol.pruebatecnicacapitol.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Before("execution(* com.capitol.pruebatecnicacapitol.service.SpaceshipService.getSpaceshipById(..)) && args(id)")
    public void logBefore(Long id) {
        if (id < 0) {
            System.out.println("Request with negative ID: " + id);
        }
    }
}

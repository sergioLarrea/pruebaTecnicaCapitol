package com.capitol.pruebatecnicacapitol.exceptions;

/**
 * Excepción personalizada para cuando no se encuentra una nave espacial.
 */
public class SpaceshipNotFoundException extends RuntimeException {
    public SpaceshipNotFoundException(String message) {
        super(message);
    }
}

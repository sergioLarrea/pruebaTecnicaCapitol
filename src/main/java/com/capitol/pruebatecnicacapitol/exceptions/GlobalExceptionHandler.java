package com.capitol.pruebatecnicacapitol.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Controlador de excepciones global que maneja las excepciones no capturadas en los controladores.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja todas las excepciones generales que no están específicamente capturadas.
     *
     * @param ex la excepción que fue lanzada
     * @param request el objeto WebRequest que contiene detalles de la solicitud
     * @return una respuesta ResponseEntity con el mensaje de la excepción y el código de estado HTTP 500 (Internal Server Error)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
        // Retorna una respuesta con el mensaje de la excepción y el estado HTTP 500
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Maneja la excepción personalizada SpaceshipNotFoundException.
     *
     * @param ex la excepción que fue lanzada
     * @param request el objeto WebRequest que contiene detalles de la solicitud
     * @return una respuesta ResponseEntity con el mensaje de la excepción y el código de estado HTTP 404 (Not Found)
     */
    @ExceptionHandler(SpaceshipNotFoundException.class)
    public ResponseEntity<?> handleSpaceshipNotFoundException(SpaceshipNotFoundException ex, WebRequest request) {
        // Retorna una respuesta con el mensaje de la excepción y el estado HTTP 404
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}

package com.devsuperior.dscommerce.controllers.handlers;

import com.devsuperior.dscommerce.dto.CustomError;
import com.devsuperior.dscommerce.dto.ValidationError;
import com.devsuperior.dscommerce.services.exceptions.DatabaseException;
import com.devsuperior.dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

/*
@ControllerAdvice, podemos definir tratamentos globais para exceções específicas,
sem precisar ficar colocando try-catch em várias partes do código.
 */
@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class) // Interceptar a Exceção
    public ResponseEntity<CustomError> resourceNotFound
            (ResourceNotFoundException e, HttpServletRequest request /* Obter a Url que deu exceção*/) {
        HttpStatus status = HttpStatus.NOT_FOUND; // Erro 404
        CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        // Retornar o objeto
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DatabaseException.class) // Interceptar a Exceção
    public ResponseEntity<CustomError> databaseException
            (DatabaseException e, HttpServletRequest request /* Obter a Url que deu exceção*/) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // Erro 400
        CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        // Retornar o objeto
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) // Interceptar a Exceção
    public ResponseEntity<CustomError> methodArgumentNotValidation
            (MethodArgumentNotValidException e, HttpServletRequest request /* Obter a Url que deu exceção*/) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY; // Erro 422
        ValidationError err = new ValidationError(Instant.now(), status.value(), "Dados Inválidos!", request.getRequestURI());

        // Verificar se há erros e adicionar na lista de erros
        for (FieldError f : e.getBindingResult().getFieldErrors()) {
            err.addError(f.getField(), f.getDefaultMessage());
        }

        // Retornar o objeto
        return ResponseEntity.status(status).body(err);
    }
}

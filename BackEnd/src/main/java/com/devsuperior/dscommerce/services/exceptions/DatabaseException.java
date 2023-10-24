package com.devsuperior.dscommerce.services.exceptions;

public class DatabaseException extends RuntimeException {
    // Exceção personalizada
    public DatabaseException(String msg) {
        super(msg);
    }
}

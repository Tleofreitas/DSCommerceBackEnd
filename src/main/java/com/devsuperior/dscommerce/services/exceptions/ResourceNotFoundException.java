package com.devsuperior.dscommerce.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    // Exceção personalizada
    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}

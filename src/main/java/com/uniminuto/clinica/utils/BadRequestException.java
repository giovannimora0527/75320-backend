package com.uniminuto.clinica.utils;

/**
 * Excepción personalizada para errores de solicitud incorrecta (400).
 * Se utiliza cuando los datos de la solicitud son inválidos o no cumplen con los requisitos.
 */
public class BadRequestException extends Exception {
    
    private static final long serialVersionUID = 1L;

    /**
     * Constructor con mensaje de error.
     *
     * @param message mensaje descriptivo del error.
     */
    public BadRequestException(String message) {
        super(message);
    }

    /**
     * Constructor con mensaje y causa del error.
     *
     * @param message mensaje descriptivo del error.
     * @param cause   la causa del error.
     */
    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}


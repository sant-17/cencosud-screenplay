package com.co.easy.exceptions;

public class DatosClienteNoCoincidenException extends RuntimeException{
    public DatosClienteNoCoincidenException(String message) {
        super(message);
    }
}

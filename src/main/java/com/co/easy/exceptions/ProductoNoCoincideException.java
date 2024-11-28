package com.co.easy.exceptions;

public class ProductoNoCoincideException extends RuntimeException {
    public ProductoNoCoincideException(String message) {
        super(message);
    }
}

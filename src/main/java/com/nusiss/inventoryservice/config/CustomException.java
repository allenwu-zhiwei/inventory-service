package com.nusiss.inventoryservice.config;

public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
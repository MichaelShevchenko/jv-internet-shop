package com.internet.shop.exceptions;

public class UnsecuredPasswordStoring extends RuntimeException {
    public UnsecuredPasswordStoring(String message, Throwable cause) {
        super(message, cause);
    }
}

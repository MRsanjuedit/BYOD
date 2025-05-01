package com.byod.userdetail.Exceptions;

public class ApiExpiredErrorException extends RuntimeException {
    public ApiExpiredErrorException(String message) {
        super(message);
    }
}

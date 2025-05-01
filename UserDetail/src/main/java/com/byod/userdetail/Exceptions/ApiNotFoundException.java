package com.byod.userdetail.Exceptions;

public class ApiNotFoundException extends RuntimeException{
    public ApiNotFoundException(String message){
        super(message);
    }
}

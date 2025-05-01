package com.byod.userdetail.Exceptions;

public class SameApiNameExistsException extends RuntimeException{
    public SameApiNameExistsException(String message){
        super(message);
    }
}

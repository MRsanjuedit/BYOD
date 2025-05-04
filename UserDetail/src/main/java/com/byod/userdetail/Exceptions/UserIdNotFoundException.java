package com.byod.userdetail.Exceptions;

import java.lang.String;

public class UserIdNotFoundException extends RuntimeException{
    public UserIdNotFoundException(String userId){
        super(userId);
    }
}

package com.byod.userdetail.Exceptions;

import java.util.UUID;

public class UserIdNotFoundException extends RuntimeException{
    public UserIdNotFoundException(UUID userId){
        super(userId.toString());
    }
}

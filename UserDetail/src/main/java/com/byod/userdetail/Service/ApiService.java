package com.byod.userdetail.Service;

import com.byod.userdetail.Model.Api;
import org.springframework.stereotype.Service;

import java.lang.String;

@Service
public interface ApiService {
    Api createApiForUser(String userId , String apiName , long expiryMinutes);
    String deleteApiForUser(String userId,String apiId);

}

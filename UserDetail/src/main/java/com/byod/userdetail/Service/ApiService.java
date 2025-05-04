package com.byod.userdetail.Service;

import com.byod.userdetail.Model.Api;
import org.springframework.stereotype.Service;

import java.lang.String;

@Service
public interface ApiService {
    Api createApiForUser(Long userId , String apiName , Long expiryMinutes);

    String deleteApiForUser(Long userId, Long apiId);

}

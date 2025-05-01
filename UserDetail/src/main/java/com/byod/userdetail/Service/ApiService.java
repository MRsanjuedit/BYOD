package com.byod.userdetail.Service;

import com.byod.userdetail.Model.Api;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface ApiService {
    Api createApiForUser(UUID userId , String apiName , long expiryMinutes);
    String deleteApiForUser(UUID userId,UUID apiId);

}

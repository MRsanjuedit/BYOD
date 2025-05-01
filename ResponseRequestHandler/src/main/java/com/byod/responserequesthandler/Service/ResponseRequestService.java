package com.byod.responserequesthandler.Service;

import org.springframework.stereotype.Service;

@Service
public interface ResponseRequestService {
    String testConnection(String uuid , String api);
    Boolean isAuthenticated(String uuid , String api);
    String doEncryption(String uuid ,String api, String plainText);
    String doDecryption(String uuid ,String api, String cipherText);
}

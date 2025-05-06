package com.byod.responserequesthandler.Service;

import org.springframework.stereotype.Service;

@Service
public interface ResponseRequestService {
    String testConnection(Long uuid , Long api);
    Boolean isAuthenticated(Long uuid , Long api);
    String doEncryption(Long uuid ,Long api, String plainText , String key) throws Exception;
    String doDecryption(Long uuid ,Long api, String cipherText , String key) throws Exception;
}

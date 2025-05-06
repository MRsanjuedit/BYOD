package com.byod.responserequesthandler.Service.Implements;

import com.byod.responserequesthandler.Security.SecurityMethodsImplement;
import com.byod.responserequesthandler.Service.ResponseRequestService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class RRSImplement implements ResponseRequestService {

    private final WebClient webClient = WebClient.create();

    public Boolean fetchDataFromUrl(String url) {
        Mono<Boolean> responseMono = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(Boolean.class);  // Expecting a Boolean directly

        return responseMono.block();  // Block to get actual Boolean value
    }

    public String decryptTheCipherText(String cipherText , String key) throws Exception {
        //perform decryption of cipher text
        return SecurityMethodsImplement.decryptTextCombined(cipherText,key);
    }
    public String encryptThePlainText(String data , String key) throws Exception {
        //perform encryption of data
        return SecurityMethodsImplement.encryptTextCombined(data,key);
    }
    public Boolean isUuidAndApiValid(Long uuid , Long apiId) {
        //some operations in our database checking is uuid and api is valid
        if(uuid == null || apiId == null) return false;
        String url = "http://localhost:8080/check/"+uuid+"/"+apiId;
        return fetchDataFromUrl(url);
    }

    @Override
    public String testConnection(Long uuid, Long apiId) {
        if(isUuidAndApiValid(uuid,apiId) ) {
            return "UUID and API are valid";
        }
        return "UUID and API are invalid";
    }

    @Override
    public Boolean isAuthenticated(Long uuid, Long api) {
        return uuid != null && api != null && isUuidAndApiValid(uuid,api);
    }

    public String doEncryption(Long uuid , Long api, String plainText , String key) throws Exception {
        if(!isAuthenticated(uuid, api)) {
            return "Authentication Failed , Issue with UUID and API";
        }
        else{
            return encryptThePlainText(plainText , key);
        }
    }

    public String doDecryption(Long uuid, Long api, String cipherText , String key) throws Exception {
        if(!isAuthenticated(uuid, api)) {
            return "Authentication Failed , Issue with UUID and API";
        }
        else{
            return decryptTheCipherText(cipherText , key);
        }
    }
}

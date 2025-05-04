package com.byod.responserequesthandler.Service.Implements;

import com.byod.responserequesthandler.Security.SecurityMethodsImplement;
import com.byod.responserequesthandler.Service.ResponseRequestService;
import org.springframework.stereotype.Service;

@Service
public class RRSImplement implements ResponseRequestService {

    public String decryptTheCipherText(String cipherText , String key) throws Exception {
        //perform decryption of cipher text
        return SecurityMethodsImplement.decryptTextCombined(cipherText,key);
    }
    public String encryptThePlainText(String data , String key) throws Exception {
        //perform encryption of data
        return SecurityMethodsImplement.encryptTextCombined(data,key);
    }
    public Boolean isUuidValid(String uuid) {
        //some operations in our database checking is uuid and api is valid
        return true;
    }
    public Boolean isApiValid(String uuid) {
        //some operations in our database checking is api is valid
        return true;
    }

    @Override
    public String testConnection(String uuid, String api) {
        if(uuid == null) return "UUID is null";
        else if(api == null) return "API is null";
        else if(!isUuidValid(uuid)) return "Invalid UUID";
        else{
            if(!isApiValid(api)) return "API is not valid";
            else return "Connection Establishment is Successful";
        }

    }

    @Override
    public Boolean isAuthenticated(String uuid, String api) {
        return uuid != null && api != null && isApiValid(uuid) && isApiValid(api);
    }

    public String doEncryption(String uuid , String api, String plainText , String key) throws Exception {
        if(!isAuthenticated(uuid, api)) {
            return "Authentication Failed , Issue with UUID and API";
        }
        else{
            return encryptThePlainText(plainText , key);
        }
    }

    public String doDecryption(String uuid, String api, String cipherText , String key) throws Exception {
        if(!isAuthenticated(uuid, api)) {
            return "Authentication Failed , Issue with UUID and API";
        }
        else{
            return decryptTheCipherText(cipherText , key);
        }
    }
}

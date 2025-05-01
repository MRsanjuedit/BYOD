package com.byod.responserequesthandler.Service.ResponseRequestServiceImplement;

import com.byod.responserequesthandler.Service.ResponseRequestService;

public class RRSImplementation implements ResponseRequestService , SecurityMethods{
    @Override
    public String decryptTheCipherText(String cipherText) {
        //perform decryption of cipher text
        return cipherText;
    }
    @Override
    public String encryptThePlainText(String data) {
        //perform encryption of data
        return data;
    }
    @Override
    public Boolean isUuidValid(String uuid) {
        //some operations in our database checking is uuid and api is valid
        return true;
    }
    @Override
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

    @Override
    public String doEncryption(String uuid , String api, String plainText) {
        if(!isAuthenticated(uuid, api)) {
            return "Authentication Failed , Issue with UUID and API";
        }
        else{
            return encryptThePlainText(plainText);
        }
    }

    @Override
    public String doDecryption(String uuid, String api, String cipherText) {
        if(!isAuthenticated(uuid, api)) {
            return "Authentication Failed , Issue with UUID and API";
        }
        else{
            return decryptTheCipherText(cipherText);
        }
    }
}

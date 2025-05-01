package com.byod.responserequesthandler.Service.ResponseRequestServiceImplement;

public interface SecurityMethods {
    public String encryptThePlainText(String data);
    public String decryptTheCipherText(String cipherText);
    public Boolean isUuidValid(String uuid);
    public Boolean isApiValid(String uuid);
}

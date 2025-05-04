package com.byod.responserequesthandler.Model;

public class DecryptRequest {
    private String encrypted;
    private String iv;

    // Getters and setters
    public String getEncrypted() {
        return encrypted;
    }

    public void setEncrypted(String encrypted) {
        this.encrypted = encrypted;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }
}

package com.byod.responserequesthandler.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Base64;

public class SecurityMethodsImplement {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    // AES-256-GCM Encryption (Combines IV + Ciphertext)
    public static String encryptTextCombined(String plainText, String key) throws Exception {
        byte[] aesKey = key.getBytes(StandardCharsets.UTF_8);
        if (aesKey.length != 32) {
            throw new IllegalArgumentException("AES key must be 32 bytes for AES-256");
        }

        SecureRandom secureRandom = new SecureRandom();
        byte[] ivBytes = new byte[12];
        secureRandom.nextBytes(ivBytes);

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding", "BC");
        SecretKeySpec secretKeySpec = new SecretKeySpec(aesKey, "AES");
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(128, ivBytes);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, gcmParameterSpec);

        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

        String encryptedTextBase64 = Base64.getEncoder().encodeToString(encryptedBytes);
        String ivBase64 = Base64.getEncoder().encodeToString(ivBytes);

        return encryptedTextBase64 + ":" + ivBase64;
    }

    // AES-256-GCM Decryption (Extracts IV + Ciphertext)
    public static String decryptTextCombined(String cipherTextAndIV, String key) throws Exception {
        String[] parts = cipherTextAndIV.split(":");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Input is not valid");
        }

        byte[] encryptedBytes = Base64.getDecoder().decode(parts[0]);
        byte[] ivBytes = Base64.getDecoder().decode(parts[1]);

        byte[] aesKey = key.getBytes(StandardCharsets.UTF_8);
        if (aesKey.length != 32) {
            throw new IllegalArgumentException("AES key must be 32 bytes for AES-256");
        }

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding", "BC");
        SecretKeySpec secretKeySpec = new SecretKeySpec(aesKey, "AES");
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(128, ivBytes);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, gcmParameterSpec);

        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }
}

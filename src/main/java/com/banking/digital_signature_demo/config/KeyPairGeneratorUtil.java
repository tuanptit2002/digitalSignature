package com.banking.digital_signature_demo.config;

import java.security.*;


public class KeyPairGeneratorUtil {
    private  KeyPair keyPair;


    public KeyPairGeneratorUtil() throws NoSuchAlgorithmException{
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        this.keyPair = keyPairGenerator.generateKeyPair();
    }

    public PrivateKey getPrivateKey(){
        return  keyPair.getPrivate();
    }

    public PublicKey getPublickey(){
        return  keyPair.getPublic();
    }

}

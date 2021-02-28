package com.flab.demo.security;

import org.springframework.stereotype.Component;

import static com.flab.demo.security.Sha256.SHA_256;

@Component
public class Sha256Encryptor implements Encryptor {

    public String encryptPasswordBySha256(String password) {
        return encrypt(password, new Sha256());
    }

    @Override
    public String encrypt(String value, Cryptographic cryptographic) {
        return cryptographic.digest(value, SHA_256);
    }
}

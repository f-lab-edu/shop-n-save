package com.flab.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import static com.flab.demo.security.Sha256.SHA_256;

@Component
public class PasswordEncoder {

    @Bean
    public String encryptPassword(String password, Cryptographic cryptographic) {
        return cryptographic.digest(password, SHA_256);
    }

    @Bean
    public String encryptPasswordBySha256(String password) {
        return encryptPassword(password, new Sha256());
    }

}

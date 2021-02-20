package com.flab.demo.domain;

import com.flab.demo.security.Cryptographic;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

import static com.flab.demo.security.Sha256.SHA_256;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {
    private Long id;

    @Email
    private String email;

    private String password;

    private String name;

    @Builder
    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public static String encryptPassword(String password, Cryptographic cryptographic) {
        return cryptographic.digest(password, SHA_256);
    }
}

package com.flab.demo.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

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
}

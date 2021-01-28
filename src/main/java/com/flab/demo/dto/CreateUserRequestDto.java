package com.flab.demo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateUserRequestDto {
    private String email;
    private String password;

    @Builder
    public CreateUserRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}

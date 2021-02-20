package com.flab.demo.dto;

import com.flab.demo.domain.User;
import com.flab.demo.security.Sha256;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import static com.flab.demo.domain.User.encryptPassword;

@Getter
@NoArgsConstructor
public class CreateUserRequestDto {
    @Email
    @NotBlank(message = "이메일은 필수값입니다.")
    private String email;

    @NotBlank(message = "패스워드는 필수값입니다.")
    private String password;

    @NotBlank(message = "이름은 필수값입니다.")
    private String name;

    @Builder
    public CreateUserRequestDto(String email, String password, String name) {
        this.email = email;
        this.password = encryptPassword(password, new Sha256());
        this.name = name;
    }

    public User toEntity() {
        return User.builder()
                .email(this.email)
                .password(this.password)
                .name(this.name)
                .build();
    }
}

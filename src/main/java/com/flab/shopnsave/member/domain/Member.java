package com.flab.shopnsave.member.domain;

import com.flab.shopnsave.enums.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Member {

    private Long id;
    private String email;
    private String password;
    private String name;
    private String address;
    private Timestamp createDate;
    private Role role;
    private String phone;

    @Builder
    public Member(@NotBlank String email, @NotBlank String password, @NotBlank String name, @NotBlank String address, @NotBlank String phone) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changeAddress(String address) {
        this.address = address;
    }

    public void changePhone(String phone) {
        this.phone = phone;
    }
}

package com.flab.shopnsave.member.domain;

import com.flab.shopnsave.enums.Role;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Builder
@Getter
public class Member {

    private Long id;
    private String email;
    private String password;
    private String name;
    private String address;
    private Timestamp createDate;
    private Role role;

    public void changePassword(String password) {
        this.password = password;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changeAddress(String address) {
        this.address = address;
    }
}

package com.flab.shopnsave.member.domain;

import com.flab.shopnsave.enums.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

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
    public Member(String email, String password, String name, String address, String phone) {
        Assert.hasText(email, "이메일이 존재하지 않습니다");
        Assert.hasText(password, "비밀번호가 존재하지 않습니다");
        Assert.hasText(name, "이름이 존재하지 않습니다");
        Assert.hasText(address, "주소가 존재하지 않습니다");
        Assert.hasText(phone, "전화번호가 존재하지 않습니다");

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

package com.flab.shopnsave.member.domain;

import com.flab.shopnsave.enums.Role;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthMember {

    private Long id;
    private String email;
    private String name;
    private Role role;
    private String address;
    private String phone;

    public static AuthMember newInstance(Member member) {
        return new AuthMember(member);
    }

    private AuthMember(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.name = member.getName();
        this.role = member.getRole();
        this.address = member.getAddress();
        this.phone = member.getPhone();
    }
}
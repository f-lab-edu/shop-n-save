package com.flab.shopnsave.member.domain;

import com.flab.shopnsave.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AuthMember {

    private Long id;
    private String email;
    private Role role;
    private String address;

    public AuthMember(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.role = member.getRole();
        this.address = member.getAddress();
    }
}
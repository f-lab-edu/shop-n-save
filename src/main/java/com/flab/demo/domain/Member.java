package com.flab.demo.domain;

import com.flab.demo.enums.Role;
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
    private Timestamp createDate;
    private Role role;
}

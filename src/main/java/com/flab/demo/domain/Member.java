package com.flab.demo.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {
    private Long id;
    private String email;
    private String password;
    private String name;
}

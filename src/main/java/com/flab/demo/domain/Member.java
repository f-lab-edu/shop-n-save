package com.flab.demo.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    private Long id;
    private String email;
    private String password;
    private String name;
}

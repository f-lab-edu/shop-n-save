package com.flab.demo.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    private Long id;
    private String email;
    private String password;
    private String name;
}

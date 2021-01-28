package com.flab.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String email;

    @Column
    private String password;

    protected User() {
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return this.email;
    }
}

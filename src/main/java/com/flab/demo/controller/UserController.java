package com.flab.demo.controller;

import com.flab.demo.domain.User;
import com.flab.demo.dto.CreateUserRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/users")
    public User create(@RequestBody CreateUserRequestDto user) {
        return new User("sgkim94@github.com", "123456");
    }
}

package com.flab.demo.controller;

import com.flab.demo.dto.CreateUserRequestDto;
import com.flab.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity create(@Valid @RequestBody CreateUserRequestDto user) {
        userService.save(user);
        return ResponseEntity.ok().build();
    }
}

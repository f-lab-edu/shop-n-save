package com.flab.demo.controller;

import com.flab.demo.domain.User;
import com.flab.demo.dto.CreateUserRequestDto;
import com.flab.demo.dto.LoginUserRequestDto;
import com.flab.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    public static final String LOGIN_SESSION_KEY = "id";

    private UserService userService;

    @PostMapping
    public ResponseEntity create(@Valid  @RequestBody CreateUserRequestDto user) {
        User savedUser = userService.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity login(@Valid  @RequestBody LoginUserRequestDto user, HttpServletRequest request) {
        User loginUser = userService.login(user);

        request.setAttribute(LOGIN_SESSION_KEY, loginUser.getId());
        return ResponseEntity.ok().build();
    }
}

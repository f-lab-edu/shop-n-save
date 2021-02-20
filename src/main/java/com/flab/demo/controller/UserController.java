package com.flab.demo.controller;

import com.flab.demo.dto.CreateUserRequestDto;
import com.flab.demo.dto.LoginUserRequestDto;
import com.flab.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.flab.demo.controller.ResponseEntityGroup.OK_RESPONSE_ENTITY;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private static final String LOGIN_USER_ID_SESSION_KEY = "userId";

    private final UserService userService;

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody CreateUserRequestDto user) {
        userService.save(user);

        return OK_RESPONSE_ENTITY.getEntity();
    }

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody LoginUserRequestDto user, HttpServletRequest request) {
        Long loginUserId = userService.login(user);

        request.setAttribute(LOGIN_USER_ID_SESSION_KEY, loginUserId);

        return ResponseEntity.ok(loginUserId);
    }
}

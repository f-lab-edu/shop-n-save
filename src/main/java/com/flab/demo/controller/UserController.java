package com.flab.demo.controller;

import com.flab.demo.dto.CreateUserRequestDto;
import com.flab.demo.dto.LoginUserRequestDto;
import com.flab.demo.security.HttpSessionUtil;
import com.flab.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.flab.demo.controller.ResponseEntityGroup.OK_RESPONSE_ENTITY;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody CreateUserRequestDto user) {
        userService.save(user);

        return OK_RESPONSE_ENTITY.getEntity();
    }

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody LoginUserRequestDto user, HttpSession session) {
        Long loginUserId = userService.login(user);

        HttpSessionUtil.addUserSession(session, loginUserId);

        return ResponseEntity.ok(loginUserId);
    }
}

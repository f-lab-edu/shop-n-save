package com.flab.demo.controller;

import com.flab.demo.domain.User;
import com.flab.demo.dto.CreateUserRequestDto;
import com.flab.demo.security.Sha256;
import com.flab.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.flab.demo.controller.ResponseEntityGroup.OK_RESPONSE_ENTITY;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity create(@Valid @RequestBody CreateUserRequestDto user) {
        User hashUser = userService.convertToUserByHashPassword(user, new Sha256());
        userService.save(hashUser);
        return OK_RESPONSE_ENTITY.getEntity();
    }
}

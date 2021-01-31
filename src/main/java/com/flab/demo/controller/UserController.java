package com.flab.demo.controller;

import com.flab.demo.domain.User;
import com.flab.demo.dto.CreateUserRequestDto;
import com.flab.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {
    
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity create(@RequestBody CreateUserRequestDto user) {
        User savedUser = userService.save(user);
        return ResponseEntity.ok(savedUser);
    }
}

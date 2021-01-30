package com.flab.demo.service;

import com.flab.demo.domain.User;
import com.flab.demo.dto.CreateUserRequestDto;
import com.flab.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(CreateUserRequestDto user) {
        User registerUser = userRepository.findByEmail(user.getEmail());

        if (registerUser != null) {
            throw new IllegalArgumentException("등록된 사용자입니다.");
        }

        return userRepository.save(user.toEntity());
    }
}

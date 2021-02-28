package com.flab.demo.service;

import com.flab.demo.domain.User;
import com.flab.demo.dto.CreateUserRequestDto;
import com.flab.demo.dto.LoginUserRequestDto;
import com.flab.demo.exception.NotAuthenticationException;
import com.flab.demo.mapper.UserMapper;
import com.flab.demo.security.Sha256Encryptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final Sha256Encryptor sha256Encryptor;

    public void save(CreateUserRequestDto user) {
        User registerUser = userMapper.findByEmail(user.getEmail());

        if (registerUser != null) {
            throw new IllegalArgumentException("등록된 사용자입니다.");
        }

        String encodedPassword = sha256Encryptor.encryptPasswordBySha256(user.getPassword());

        int addCount = userMapper.save(user.toEntity(encodedPassword));

        if (addCount == 0) {
            throw new RuntimeException("사용자 정보를 저장에 실패하였습니다.");
        }
    }

    public Long findByEmailAndPassword(LoginUserRequestDto user) {
        String encodedPassword = sha256Encryptor.encryptPasswordBySha256(user.getPassword());

        User registerUser = userMapper.findByEmailAndPassword(user.toDto(encodedPassword));

        if (registerUser == null) {
            throw new NotAuthenticationException("사용자 정보가 존재하지 않습니다.");
        }

        return registerUser.getId();
    }
}

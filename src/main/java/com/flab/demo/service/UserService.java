package com.flab.demo.service;

import com.flab.demo.domain.User;
import com.flab.demo.dto.CreateUserRequestDto;
import com.flab.demo.dto.LoginUserRequestDto;
import com.flab.demo.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    public void save(CreateUserRequestDto user) {
        User registerUser = userMapper.findByEmail(user.getEmail());

        if (registerUser != null) {
            throw new IllegalArgumentException("등록된 사용자입니다.");
        }

        int addCount = userMapper.save(user.toEntity());

        if (addCount == 0) {
            throw new RuntimeException("사용자 정보를 저장에 실패하였습니다.");
        }
    }

    public Long login(LoginUserRequestDto user) {
        User registerUser = userMapper.findByEmail(user.getEmail());

        if (registerUser == null) {
            throw new IllegalArgumentException("사용자가 존재하지 않습니다.");
        }

        return registerUser.getId();
    }
}

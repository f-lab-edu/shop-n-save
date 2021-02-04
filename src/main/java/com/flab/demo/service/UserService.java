package com.flab.demo.service;

import com.flab.demo.domain.User;
import com.flab.demo.dto.CreateUserRequestDto;
import com.flab.demo.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.flab.demo.utils.SHAUtil.SHA_256;
import static com.flab.demo.utils.SHAUtil.digest;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    public void save(CreateUserRequestDto user) {
        User registerUser = userMapper.findByEmail(user.getEmail());

        if (registerUser != null) {
            throw new IllegalArgumentException("등록된 사용자입니다.");
        }

        String encryptPassword = digest(user.getPassword(), SHA_256);

        int addCount = userMapper.save(user.toEntity(encryptPassword));

        if (addCount == 0) {
            throw new RuntimeException("사용자 정보를 저장에 실패하였습니다.");
        }
    }
}

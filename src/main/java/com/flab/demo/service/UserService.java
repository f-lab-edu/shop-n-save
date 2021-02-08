package com.flab.demo.service;

import com.flab.demo.domain.User;
import com.flab.demo.dto.CreateUserRequestDto;
import com.flab.demo.security.Cryptographic;
import com.flab.demo.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.flab.demo.security.Sha256.SHA_256;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    public void save(User user) {
        User registerUser = userMapper.findByEmail(user.getEmail());

        if (registerUser != null) {
            throw new IllegalArgumentException("등록된 사용자입니다.");
        }

        int addCount = userMapper.save(user);

        if (addCount == 0) {
            throw new RuntimeException("사용자 정보를 저장에 실패하였습니다.");
        }
    }

    public User convertToUserByHashPassword(CreateUserRequestDto user, Cryptographic cryptographic) {
        return user.toEntity(cryptographic.digest(user.getPassword(), SHA_256));
    }
}

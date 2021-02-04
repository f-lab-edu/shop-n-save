package com.flab.demo.mapper;

import com.flab.demo.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    int save(User user);
    User findByEmail(String email);
}

package com.flab.demo.repository;

import com.flab.demo.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.flab.demo.fixture.UserFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void findByEmail() {
        //given
        User savedUser = userRepository.save(TEST_USER);

        //when
        User findUser = userRepository.findByEmail(savedUser.getEmail());

        //then
        assertThat(findUser.getEmail()).isEqualTo(TEST_EMAIL);
        assertThat(findUser.getPassword()).isEqualTo(TEST_PASSWORD);
    }
}

package com.flab.demo.repository;

import com.flab.demo.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.TransactionSystemException;

import static com.flab.demo.fixture.UserFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("User 정보를 email 로 검색할 수 있는지")
    void findByEmail() {
        //given
        User savedUser = userRepository.save(TEST_USER);

        //when
        User findUser = userRepository.findByEmail(savedUser.getEmail());

        //then
        assertThat(findUser.getEmail()).isEqualTo(TEST_EMAIL);
        assertThat(findUser.getPassword()).isEqualTo(TEST_PASSWORD);
    }

    @Test
    @DisplayName("사용자 정보 저장 시 email 형식이 아닌 경우 예외처리 하는지")
    void save_not_valid_email() {
        assertThrows(TransactionSystemException.class,
                () -> userRepository.save(new User("abc", "234565"))
        );
    }
}

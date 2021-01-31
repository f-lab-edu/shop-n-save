package com.flab.demo.security;

import com.flab.demo.domain.User;
import com.flab.demo.repository.UserRepository;
import com.flab.demo.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.flab.demo.fixture.UserFixture.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("사용자 회원가입 시 존재하는 email 인 상황일 때 IllegalArgumentException 예외가 발생한다.")
    void save_fail_when_exist_user_email() {
        when(userRepository.findByEmail(TEST_EMAIL)).thenReturn(TEST_USER);

        String errorMessage = assertThrows(IllegalArgumentException.class,
                () -> userService.save(CREATE_USER_REQUEST_DTO)
        ).getMessage();

        assertThat(errorMessage).isEqualTo("등록된 사용자입니다.");
    }

    @Test
    @DisplayName("올바른 형태의 email 과 password 를 입력받은 경우 User 테이블에 저장된 후 User 를 리턴한다.")
    void save() {
        when(userRepository.save(any())).thenReturn(TEST_USER);

        User savedUser = userService.save(CREATE_USER_REQUEST_DTO);

        assertThat(savedUser.getEmail()).isEqualTo(TEST_EMAIL);
        assertThat(savedUser.getPassword()).isEqualTo(TEST_PASSWORD);
    }

    @Test
    @DisplayName("올바른 형태의 email 과 password 를 입력받아 login 을 시도하는 경우 해당 User 를 리턴한다.")
    void login() {
        when(userRepository.findByEmail(any())).thenReturn(TEST_USER);

        User savedUser = userService.login(LOGIN_USER_REQUEST_DTO);

        assertThat(savedUser.getId()).isNotNull();
    }
}

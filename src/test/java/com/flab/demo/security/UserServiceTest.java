package com.flab.demo.security;

import com.flab.demo.domain.User;
import com.flab.demo.dto.CreateUserRequestDto;
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
    @DisplayName("사용자 회원가입 시 존재하는 email인 경우 예외처리를 하는지")
    void save_fail_when_exist_user_email() {
        when(userRepository.findByEmail(TEST_EMAIL)).thenReturn(new User(TEST_EMAIL, TEST_PASSWORD));

        String errorMessage = assertThrows(IllegalArgumentException.class,
                () -> userService.save(new CreateUserRequestDto(TEST_EMAIL, TEST_PASSWORD))
        ).getMessage();

        assertThat(errorMessage).isEqualTo("등록된 사용자입니다.");
    }

    @Test
    @DisplayName("이메일과 패스워드를 입력받아 회원가입을 할 수 있는지")
    void save() {
        when(userRepository.save(any())).thenReturn(TEST_USER);

        User savedUser = userService.save(CREATE_USER_REQUEST_DTO);

        assertThat(savedUser.getEmail()).isEqualTo(TEST_EMAIL);
        assertThat(savedUser.getPassword()).isEqualTo(TEST_PASSWORD);
    }
}

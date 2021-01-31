package com.flab.demo.controller;

import com.flab.demo.domain.User;
import com.flab.demo.dto.CreateUserRequestDto;
import com.flab.demo.dto.LoginUserRequestDto;
import com.flab.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static com.flab.demo.fixture.UserFixture.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class UserControllerTest {
    private static final String USERS_BASE_URL = "/users";

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("올바른 email 과 password 를 입력받은 경우 User 테이블에 저장되며 User 정보와 함께 ok status 를 리턴한다.")
    void create_user_test() {
        // when
        User savedUser = creatUser(CREATE_USER_REQUEST_DTO);

        // then
        assertThat(savedUser.getEmail()).isEqualTo(TEST_EMAIL);
        assertThat(savedUser.getPassword()).isEqualTo(TEST_PASSWORD);
    }

    @Test
    @DisplayName("회원가입된 email 과 password 를 입력받아 로그인 시도하는 경우 해당 회원 ID 를 ok status 와 함께 리턴한다.")
    void login_test() {
        // given
        creatUser(CREATE_USER_REQUEST_DTO);

        LoginUserRequestDto user = LoginUserRequestDto.builder()
                .email(TEST_EMAIL)
                .password(TEST_PASSWORD)
                .build();

        // when
        webTestClient.post()
                .uri(USERS_BASE_URL + "/login")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(user), LoginUserRequestDto.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(User.class)
                .returnResult()
                .getResponseBody();
    }

    private User creatUser(CreateUserRequestDto request) {
        return webTestClient.post()
                .uri(USERS_BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), CreateUserRequestDto.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(User.class)
                .returnResult()
                .getResponseBody();
    }
}

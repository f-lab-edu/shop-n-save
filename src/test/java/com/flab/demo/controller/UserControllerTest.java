package com.flab.demo.controller;

import com.flab.demo.dto.CreateUserRequestDto;
import com.flab.demo.dto.LoginUserRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static com.flab.demo.fixture.UserFixture.CREATE_USER_REQUEST_DTO;
import static com.flab.demo.fixture.UserFixture.LOGIN_USER_REQUEST_DTO;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class UserControllerTest {
    public static final String USER_BASE_URL = "/users";

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("올바른 email 과 password 를 입력받은 경우 User 테이블에 저장되며 User 정보와 함께 ok status 를 리턴한다.")
    @Sql(scripts = "/clean-all.sql")
    void createUserTest() {
        //when
        createUser();
    }

    @Test
    @DisplayName("올바른 email 과 password 를 입력받은 경우 세션에 사용자 정보 저장 후 사용자 ID 를 리턴한다.")
    @Sql(scripts = "/clean-all.sql")
    void loginUserTest() {
        //given
        createUser();

        //when
        Long loginUserId = webTestClient.post()
                .uri(USER_BASE_URL + "/login")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(LOGIN_USER_REQUEST_DTO), LoginUserRequestDto.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Long.class)
                .returnResult().getResponseBody();

        assertThat(loginUserId).isNotNull();
    }

    private void createUser() {
        webTestClient.post()
                .uri(USER_BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(CREATE_USER_REQUEST_DTO), CreateUserRequestDto.class)
                .exchange()
                .expectStatus().isOk();
    }
}

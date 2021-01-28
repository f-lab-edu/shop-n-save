package com.flab.demo.controller;

import com.flab.demo.domain.User;
import com.flab.demo.dto.CreateUserRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient(timeout = "100000")
public class UserControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    @DisplayName("아이디와 비밀번호를 입력받아 회원가입에 성공하는지")
    void createUser() {
        // given
        String userName = "sgkim94@github.com";
        String passWord = "123456";

        CreateUserRequestDto user = CreateUserRequestDto.builder()
                .email(userName)
                .password(passWord)
                .build();

        // when
        User savedUser = webTestClient.post()
                .uri("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(user), CreateUserRequestDto.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(User.class)
                .returnResult().getResponseBody();

        // then
        assertThat(savedUser.getEmail()).isEqualTo(userName);
        assertThat(savedUser.getPassword()).isEqualTo(passWord);
    }
}

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

import static com.flab.demo.fixture.UserFixture.CREATE_USER_REQUEST_DTO;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class UserControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("올바른 email 과 password 를 입력받은 경우 User 테이블에 저장되며 User 정보와 함께 ok status 를 리턴한다.")
    void createUser() {
        // when
        User savedUser = webTestClient.post()
                .uri("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(CREATE_USER_REQUEST_DTO), CreateUserRequestDto.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(User.class)
                .returnResult().getResponseBody();
    }
}

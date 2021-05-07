package com.flab.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.demo.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class MemberControllerIntegrationTest {

    private Member member;

    @BeforeEach
    public void setUp() {
        member = new Member().builder()
                .email("test1223@test")
                .password("pw")
                .name("testName")
                .build();
    }

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("올바른 형태의 email 과 password 를 입력받은 경우 Member 테이블에 저장하고 member 객체를 반환한다")
    public void join() throws Exception {
        // given
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(member);

        // when
        // then
        mockMvc.perform(post("/members")
                    .content(jsonString)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(member.getEmail()))
                .andExpect(jsonPath("$.password").value(member.getPassword()))
                .andExpect(jsonPath("$.name").value(member.getName()));

    }

    @Test
    @DisplayName("이미 가입된 이메일로 회원가입을 시도하는 경우 400 상태코드를 응답받는다")
    public void duplicated_email_join() throws Exception {
        // given
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(member);

        mockMvc.perform(post("/members")
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(post("/members")
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("올바르지 않은 이메일 형식을 입력하여 회원가입을 시도하는 경우 400 상태코드를 응답받는다")
    public void invalid_email_join() throws Exception {
        // given
        Member member = new Member().builder()
                .email("123")
                .password("pw")
                .name("testName")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(member);

        mockMvc.perform(post("/members")
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
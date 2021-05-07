package com.flab.demo.system;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.demo.domain.Member;
import com.flab.demo.exception.UserAuthenticationFailException;
import com.flab.demo.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class HttpSessionAuthentificationTest {

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
    private MemberService memberService;

    @Autowired
    private Authentification authentification;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("등록되지 않은 Member 정보로 로그인을 시도하는 경우 UserAuthenticationFailException 예외가 발생한다")
    public void unregistered_user_join() {
        // given
        Member member1 = new Member().builder()
                .email("unknown@abc.aaa")
                .password("1234")
                .name("mina")
                .build();
        // when
        UserAuthenticationFailException e = assertThrows(UserAuthenticationFailException.class, () -> authentification.login(member1));

        // then
        assertThat(e.getMessage()).isEqualTo("아이디가 존재하지 않거나 비밀번호가 틀립니다.");
    }

    @Test
    @DisplayName("비밀번호 오입력하여 로그인을 시도하는 경우 UserAuthenticationFailException 예외가 발생한다")
    public void 비밀번호_오입력_사용자_로그인_예외() {
        // given
        Member member1 = new Member().builder()
                .email("test1243@abc")
                .password("1234")
                .name("mina")
                .build();

        Member member2 = new Member().builder()
                .email("test1243@abc")
                .password("4321")
                .name("mina")
                .build();

        memberService.join(member1);

        // when
        UserAuthenticationFailException e = assertThrows(UserAuthenticationFailException.class, () -> authentification.login(member2));

        // then
        assertThat(e.getMessage()).isEqualTo("아이디가 존재하지 않거나 비밀번호가 틀립니다.");
    }

    @Test
    @DisplayName("올바른 정보의 아이디와 비밀번호로 로그인을 시도하는 경우 응답 헤더에 set-cookie 속성이 셋팅된다")
    public void join() throws Exception {
        // given
        memberService.join(member);

        String jsonString = objectMapper.writeValueAsString(member);

        // when
        mockMvc.perform(post("/members/login")
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().exists(HttpHeaders.SET_COOKIE));
    }
}
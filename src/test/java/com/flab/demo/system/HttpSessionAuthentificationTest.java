package com.flab.demo.system;

import com.flab.demo.domain.Member;
import com.flab.demo.exception.UserAuthenticationFailException;
import com.flab.demo.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class HttpSessionAuthentificationTest {

    @Autowired
    private SessionAuthentification authentification;

    @Autowired
    private MemberService memberService;

    @Test
    public void 등록되지않은_사용자_로그인_예외() {
        // given
        Member member1 = new Member().builder()
                .email("test@abc")
                .password("1234")
                .name("mina")
                .build();
        MockHttpSession session = new MockHttpSession();

        // when
        UserAuthenticationFailException e = assertThrows(UserAuthenticationFailException.class, () -> authentification.login(member1));

        // then
        assertThat(e.getMessage()).isEqualTo("아이디가 존재하지 않거나 비밀번호가 틀립니다.");
    }

    @Test
    public void 비밀번호_오입력_사용자_로그인_예외() {
        // given
        Member member1 = new Member().builder()
                .email("test@abc")
                .password("1234")
                .name("mina")
                .build();

        Member member2 = new Member().builder()
                .email("test@abc")
                .password("4321")
                .name("mina")
                .build();
        memberService.join(member1);
        MockHttpSession session = new MockHttpSession();

        // when
        UserAuthenticationFailException e = assertThrows(UserAuthenticationFailException.class, () -> authentification.login(member2));

        // then
        assertThat(e.getMessage()).isEqualTo("아이디가 존재하지 않거나 비밀번호가 틀립니다.");
    }

    @Test
    public void 정상_사용자_로그인() {
        // given
        Member member1 = new Member().builder()
                .email("test@abc")
                .password("1234")
                .name("mina")
                .build();

        memberService.join(member1);
        MockHttpSession session = new MockHttpSession();

        // when
        authentification.login(member1);

        // then
        // Member sessionMember = (Member)session.getAttribute(authentification.LOGIN);
        // assertThat(sessionMember).isNotNull();
        // assertThat(sessionMember.getEmail()).isEqualTo(member1.getEmail());
    }
}
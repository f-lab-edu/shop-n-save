package com.flab.demo.service;

import com.flab.demo.domain.Member;
import com.flab.demo.exception.UserAuthenticationFailException;
import com.flab.demo.session.SessionNames;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class MemberServiceTest implements SessionNames {

    @Autowired
    private MemberService memberService;

    @Test
    public void getByIdTest() {
        Member findMember = memberService.getById("known_id");
        assertNull(findMember);
    }

    @Test
    public void 회원가입() {
        // given
        Member member = new Member().builder()
                .email("test@abc")
                .password("pw")
                .name("testName")
                .build();

        // when
        Member newMember = memberService.create(member);

        // then
        Member findMember = memberService.getById(newMember.getId().toString());
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member().builder()
                .email("rewq@abc")
                .password("pw")
                .name("huimin")
                .build();

        Member member2 = new Member().builder()
                .email("rewq@abc")
                .password("pw")
                .name("huimin")
                .build();

        // when
        memberService.create(member1);
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> memberService.create(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

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
        UserAuthenticationFailException e = assertThrows(UserAuthenticationFailException.class, () -> memberService.login(member1, session));

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
        memberService.create(member1);
        MockHttpSession session = new MockHttpSession();

        // when
        UserAuthenticationFailException e = assertThrows(UserAuthenticationFailException.class, () -> memberService.login(member2, session));

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

        memberService.create(member1);
        MockHttpSession session = new MockHttpSession();

        // when
        memberService.login(member1, session);

        // then
        Member sessionMember = (Member)session.getAttribute(LOGIN);
        assertThat(sessionMember).isNotNull();
        assertThat(sessionMember.getEmail()).isEqualTo(member1.getEmail());
    }
}
package com.flab.demo.service;

import com.flab.demo.domain.Member;
import com.flab.demo.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest {

    @Autowired
    private MemberService memberService;

    @Test
    public void getByIdTest() {
        Member findMember = memberService.getById("unknown_id");
        assertNull(findMember);
    }

    @Test
    @DisplayName("올바른 형태의 email 과 password 를 입력받은 경우 Member 테이블에 저장한다")
    public void join() {
        // given
        Member member = new Member().builder()
                .email("test1253@test")
                .password("pw")
                .name("testName1253")
                .build();

        // when
        Member newMember = memberService.join(member);

        // then
        Member findMember = memberService.getById(newMember.getId().toString());
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    @DisplayName("이미 가입된 이메일로 회원가입을 시도하는 경우 IllegalArgumentException이 발생한다")
    public void duplicated_email_join() {
        // given
        Member member1 = new Member().builder()
                .email("rewq1234@abc")
                .password("pw")
                .name("huimin")
                .build();

        Member member2 = new Member().builder()
                .email("rewq1234@abc")
                .password("pw")
                .name("huimin")
                .build();

        // when
        memberService.join(member1);

        // then
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}
package com.flab.demo.service;

import com.flab.demo.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class MemberServiceTest {

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
}
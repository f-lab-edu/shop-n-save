package com.flab.demo.service;

import com.flab.demo.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setEmail("test@abc");
        member.setPassword("pw");
        member.setName("testName");

        // when
        Member newMember = memberService.create(member);

        // then
        Member findMember = memberService.getById(newMember.getId().toString());
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setEmail("rewq@abc");
        member1.setPassword("pw");
        member1.setName("huimin");

        Member member2 = new Member();
        member2.setEmail("rewq@abc");
        member2.setPassword("pwa");
        member2.setName("huimind");

        // when
        memberService.create(member1);
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> memberService.create(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}
package com.flab.demo.service;

import com.flab.demo.domain.Member;
import com.flab.demo.dto.member.CreateMemberRequestDto;
import com.flab.demo.exception.member.DuplicatedMemberException;
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
        Member foundMember = memberService.getById("unknown_id");
        assertNull(foundMember);
    }

    @Test
    @DisplayName("올바른 형태의 email 과 password 를 입력받은 경우 Member 테이블에 저장한다")
    public void join() {
        // given
        CreateMemberRequestDto createMemberRequestDto = new CreateMemberRequestDto().builder()
                .email("test1253@test")
                .password("pw")
                .name("testName1253")
                .build();

        // when
        Member newMember = memberService.join(createMemberRequestDto);

        // then
        Member foundMember = memberService.getById(newMember.getId().toString());
        assertThat(createMemberRequestDto.getName()).isEqualTo(foundMember.getName());
    }

    @Test
    @DisplayName("이미 가입된 이메일로 회원가입을 시도하는 경우 IllegalArgumentException이 발생한다")
    public void duplicated_email_join() {
        // given
        CreateMemberRequestDto createMemberRequestDto1 = new CreateMemberRequestDto().builder()
                .email("rewq1234@abc")
                .password("pw")
                .name("huimin")
                .build();

        CreateMemberRequestDto createMemberRequestDto2 = new CreateMemberRequestDto().builder()
                .email("rewq1234@abc")
                .password("pw")
                .name("huimin")
                .build();

        // when
        memberService.join(createMemberRequestDto1);

        // then
        DuplicatedMemberException e = assertThrows(DuplicatedMemberException.class, () -> memberService.join(createMemberRequestDto2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}
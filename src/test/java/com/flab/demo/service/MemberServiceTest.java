package com.flab.demo.service;

import com.flab.demo.member.domain.Member;
import com.flab.demo.member.dto.CreateMemberRequestDto;
import com.flab.demo.member.exception.DuplicatedMemberException;
import com.flab.demo.member.mapper.MemberMapper;
import com.flab.demo.member.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    private CreateMemberRequestDto createMemberRequestDto;

    @BeforeEach
    public void setUp() {
        createMemberRequestDto = CreateMemberRequestDto.builder()
                .email("test1223@test")
                .password("pw")
                .name("testName")
                .build();
    }

    @Mock
    private MemberMapper memberMapper;

    @InjectMocks
    private MemberService memberService;

    @Test
    @DisplayName("올바른 형태의 email 과 password 를 입력받은 경우 Member 테이블에 저장한다")
    public void join() {
        // given
        when(memberMapper.getByEmail(createMemberRequestDto.getEmail())).thenReturn(empty());

        // when
        memberService.join(createMemberRequestDto);

        // then
        verify(memberMapper).getByEmail(createMemberRequestDto.getEmail());
        verify(memberMapper).create(any(Member.class));
    }

    @Test
    @DisplayName("이미 가입된 이메일로 회원가입을 시도하는 경우 DuplicatedMemberException이 발생한다")
    public void duplicated_email_join() {
        // given
        when(memberMapper.getByEmail(createMemberRequestDto.getEmail())).thenReturn(ofNullable(createMemberRequestDto.toEntity()));

        // when, then
        DuplicatedMemberException e = assertThrows(DuplicatedMemberException.class, () -> memberService.join(createMemberRequestDto));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}
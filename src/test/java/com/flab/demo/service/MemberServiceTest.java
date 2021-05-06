package com.flab.demo.service;

import com.flab.demo.domain.Member;
import com.flab.demo.mapper.MemberMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    private Member member;

    @BeforeEach
    public void setUp() {
        member = new Member().builder()
                .email("test1223@test")
                .password("pw")
                .name("testName")
                .build();
    }

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberMapper memberMapper;

    @Test
    public void 정상_회원가입() {
        when(memberMapper.getByEmail(member.getEmail())).thenReturn(null);
        when(memberMapper.create(member)).thenReturn(1);

        // then
        memberService.join(member);
        verify(memberMapper, times(1)).create(member);
    }

    @Test
    public void 중복_회원_예외() {
        // given
        when(memberMapper.getByEmail(member.getEmail())).thenReturn(member);

        // when
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> memberService.join(member));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}
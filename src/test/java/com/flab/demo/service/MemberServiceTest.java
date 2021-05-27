package com.flab.demo.service;

import com.flab.demo.dto.CreateMemberRequestDto;
import com.flab.demo.exception.member.DuplicatedMemberException;
import com.flab.demo.mapper.MemberMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateMemberRequestDtoServiceTest {

    private CreateMemberRequestDto createMemberRequestDto;

    @BeforeEach
    public void setUp() {
        createMemberRequestDto = new CreateMemberRequestDto().builder()
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
    @DisplayName("올바른 형태의 email 과 password 를 입력받은 경우 Member 테이블에 저장한다")
    public void join() {
        when(memberMapper.getByEmail(createMemberRequestDto.getEmail())).thenReturn(null);
        when(memberMapper.create(createMemberRequestDto)).thenReturn(1);

        // then
        memberService.join(createMemberRequestDto);
        verify(memberMapper, times(1)).create(createMemberRequestDto);
    }

    @Test
    @DisplayName("이미 가입된 이메일로 회원가입을 시도하는 경우 IllegalArgumentException이 발생한다")
    public void duplicated_email_join() {
        // given
        when(memberMapper.getByEmail(createMemberRequestDto.getEmail())).thenReturn(createMemberRequestDto);

        // when
        DuplicatedMemberException e = assertThrows(DuplicatedMemberException.class, () -> memberService.join(createMemberRequestDto));
        assertThat(e.getErrorCode().getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}
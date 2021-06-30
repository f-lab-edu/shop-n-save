package com.flab.demo.system;

import com.flab.demo.dto.member.LoginMemberRequestDto;
import com.flab.demo.exception.member.UserAuthenticationFailException;
import com.flab.demo.mapper.MemberMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpSession;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HttpSessionAuthenticationTest {

    private LoginMemberRequestDto loginMemberRequestDto;

    @Mock
    private MemberMapper memberMapper;

    @Mock
    private HttpSession session;

    @InjectMocks
    SessionAuthentication sessionAuthentication;

    @BeforeEach
    public void setUp() {
        loginMemberRequestDto = LoginMemberRequestDto.builder()
                .email("test1223@test")
                .password("pw")
                .build();
    }

    @Test
    @DisplayName("등록되지 않은 Member 정보로 로그인을 시도하는 경우 UserAuthenticationFailException 예외가 발생한다")
    public void unregistered_user_join() {
        // given
        when(memberMapper.getByEmail(loginMemberRequestDto.getEmail())).thenReturn(null);

        // when, then
        UserAuthenticationFailException e = assertThrows(UserAuthenticationFailException.class, () -> sessionAuthentication.login(loginMemberRequestDto));
        assertThat(e.getMessage()).isEqualTo("아이디가 존재하지 않거나 비밀번호가 틀립니다.");
    }

    @Test
    @DisplayName("잘못된 password 정보로 로그인을 시도하는 경우 UserAuthenticationFailException 예외가 발생한다")
    public void wrong_password_user_join() {
        // given
        LoginMemberRequestDto loginMemberRequestDtoWithWrongPW = LoginMemberRequestDto.builder()
                .email("test1223@test")
                .password("wrongpw")
                .build();
        when(memberMapper.getByEmail(loginMemberRequestDtoWithWrongPW.getEmail())).thenReturn(loginMemberRequestDto.toEntity());

        // when, then
        UserAuthenticationFailException e = assertThrows(UserAuthenticationFailException.class, () -> sessionAuthentication.login(loginMemberRequestDtoWithWrongPW));
        assertThat(e.getMessage()).isEqualTo("아이디가 존재하지 않거나 비밀번호가 틀립니다.");
    }

    @Test
    @DisplayName("올바른 정보의 아이디와 비밀번호로 로그인을 시도하는 경우 응답 헤더에 set-cookie 속성이 셋팅된다")
    public void join() throws Exception {
        // given
        when(memberMapper.getByEmail(loginMemberRequestDto.getEmail())).thenReturn(loginMemberRequestDto.toEntity());

        // when
        sessionAuthentication.login(loginMemberRequestDto);

        // then
        verify(session).getAttribute(SessionAuthentication.LOGIN);
        verify(session).setAttribute(eq(SessionAuthentication.LOGIN), anyString());
    }
}

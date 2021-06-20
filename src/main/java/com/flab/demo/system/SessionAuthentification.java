package com.flab.demo.system;

import com.flab.demo.domain.Member;
import com.flab.demo.dto.CreateMemberRequestDto;
import com.flab.demo.dto.LoginMemberRequestDto;
import com.flab.demo.exception.member.UserAuthenticationFailException;
import com.flab.demo.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
@RequiredArgsConstructor
public class SessionAuthentification implements Authentification {

    private final HttpSession session;
    private final MemberMapper memberMapper;
    public static final String LOGIN = "loginUser";

    @Override
    public void login(LoginMemberRequestDto loginMemberRequestDto) {
        if(session.getAttribute(LOGIN) != null) {
            session.invalidate();
        }

        Member foundMember = memberMapper.getByEmail(loginMemberRequestDto.getEmail());

        if(foundMember != null && StringUtils.equals(loginMemberRequestDto.getPassword(), foundMember.getPassword())) {
            session.setAttribute(LOGIN, foundMember.getEmail());
        } else {
            throw new UserAuthenticationFailException();
        }
    }

    @Override
    public String getLoginMemberEmail() {
        return (String)session.getAttribute(LOGIN);
    }
}

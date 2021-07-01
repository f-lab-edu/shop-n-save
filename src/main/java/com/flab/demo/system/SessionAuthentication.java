package com.flab.demo.system;

import com.flab.demo.domain.AuthMember;
import com.flab.demo.domain.Member;
import com.flab.demo.dto.member.LoginMemberRequestDto;
import com.flab.demo.exception.member.NotFoundMemberException;
import com.flab.demo.exception.member.UserAuthenticationFailException;
import com.flab.demo.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
@RequiredArgsConstructor
public class SessionAuthentication implements Authentication {

    private final HttpSession session;
    private final MemberMapper memberMapper;
    public static final String LOGIN = "loginUser";

    @Override
    public void login(LoginMemberRequestDto loginMemberRequestDto) {
        if(session.getAttribute(LOGIN) != null) {
            session.invalidate();
        }

        Member foundMember = memberMapper.getByEmail(loginMemberRequestDto.getEmail()).orElseThrow(NotFoundMemberException::new);

        if(StringUtils.equals(loginMemberRequestDto.getPassword(), foundMember.getPassword())) {
            AuthMember authMember = new AuthMember(foundMember);
            session.setAttribute(LOGIN, JsonUtil.toJsonString(authMember));
        } else {
            throw new UserAuthenticationFailException();
        }
    }

    @Override
    public String getLoginMemberEmail() {
        AuthMember authMember = JsonUtil.toObject((String)session.getAttribute(LOGIN), AuthMember.class);
        return authMember.getEmail();
    }

    @Override
    public Long getLoginMemberId() {
        AuthMember authMember = JsonUtil.toObject((String)session.getAttribute(LOGIN), AuthMember.class);
        return authMember.getId();
    }

    @Override
    public AuthMember getLoginMember() {
        return JsonUtil.toObject((String)session.getAttribute(LOGIN), AuthMember.class);
    }

    @Override
    public void logout() {
        session.invalidate();
    }
}

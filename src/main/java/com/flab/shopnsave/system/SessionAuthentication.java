package com.flab.shopnsave.system;

import com.flab.shopnsave.member.domain.AuthMember;
import com.flab.shopnsave.member.domain.Member;
import com.flab.shopnsave.member.dto.LoginMemberRequestDto;
import com.flab.shopnsave.member.exception.NotFoundMemberException;
import com.flab.shopnsave.member.exception.UserAuthenticationFailException;
import com.flab.shopnsave.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SessionAuthentication implements Authentication {

    private final HttpSession session;
    private final MemberMapper memberMapper;
    public static final String LOGIN = "loginUser";

    @Override
    public void login(LoginMemberRequestDto loginMemberRequestDto) {
        if (session.getAttribute(LOGIN) != null) {
            session.invalidate();
        }

        Member foundMember = memberMapper.getByEmail(loginMemberRequestDto.getEmail()).orElseThrow(NotFoundMemberException::new);

        if (StringUtils.equals(loginMemberRequestDto.getPassword(), foundMember.getPassword())) {
            AuthMember authMember = AuthMember.newInstance(foundMember);
            session.setAttribute(LOGIN, JsonUtil.toJsonString(authMember));
        } else {
            throw new UserAuthenticationFailException();
        }
    }

    @Override
    public Optional<AuthMember> getLoginMember() {
        if (session.getAttribute(LOGIN) == null)
            return Optional.empty();
        return Optional.of(JsonUtil.toObject((String) session.getAttribute(LOGIN), AuthMember.class));
    }

    @Override
    public void logout() {
        session.invalidate();
    }
}

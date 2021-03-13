package com.flab.demo.system;

import com.flab.demo.domain.Member;
import com.flab.demo.exception.UserAuthenticationFailException;
import com.flab.demo.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
@RequiredArgsConstructor
public class HttpSessionAuthentification implements Authentification {

    private final MemberMapper memberMapper;
    public static final String LOGIN = "loginUser";

    @Override
    public Member login(Member member, HttpSession session) {
        if(session.getAttribute(LOGIN) != null) {
            session.invalidate();
        }

        Member foundMember = memberMapper.getByEmail(member.getEmail());

        if(foundMember != null && StringUtils.equals(foundMember.getPassword(), member.getPassword())) {
            session.setAttribute(LOGIN, foundMember);
        }
        else {
            throw new UserAuthenticationFailException("아이디가 존재하지 않거나 비밀번호가 틀립니다.");
        }
        return member;
    }
}

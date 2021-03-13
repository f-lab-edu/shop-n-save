package com.flab.demo.service;

import com.flab.demo.domain.Member;
import com.flab.demo.exception.UserAuthenticationFailException;
import com.flab.demo.mapper.MemberMapper;
import com.flab.demo.session.SessionNames;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class MemberService implements SessionNames {

    private final MemberMapper memberMapper;

    public Member create(Member member) {
        if(memberMapper.getByEmail(member.getEmail()) != null) {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }

        memberMapper.create(member);
        return member;
    }

    public Member getById(String id) {
        return memberMapper.getById(id);
    }

    public void login(Member member, HttpSession session) {
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
    }
}

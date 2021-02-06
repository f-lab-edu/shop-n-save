package com.flab.demo.service;

import com.flab.demo.domain.Member;
import com.flab.demo.mapper.MemberMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberMapper memberMapper;

    public MemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    public Member create(Member member) {
        if(memberMapper.getByEmail(member.getEmail()) != null) {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }

        memberMapper.create(member);
        return member;
    }

    public List<Member> getAll() {
        return memberMapper.getAll();
    }

    public Member getById(String id) {
        return memberMapper.getById(id);
    }
}

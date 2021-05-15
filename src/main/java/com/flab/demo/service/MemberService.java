package com.flab.demo.service;

import com.flab.demo.domain.Member;
import com.flab.demo.exception.ErrorCode;
import com.flab.demo.exception.member.DuplicatedMemberException;
import com.flab.demo.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;

    public Member join(Member member) {
        if(memberMapper.getByEmail(member.getEmail()) != null) {
            throw new DuplicatedMemberException(ErrorCode.EMAIL_DUPLICATION);
        }

        memberMapper.create(member);
        return member;
    }

    public Member getById(String id) {
        return memberMapper.getById(id);
    }
}

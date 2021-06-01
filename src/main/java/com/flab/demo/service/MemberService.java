package com.flab.demo.service;

import com.flab.demo.domain.Member;
import com.flab.demo.dto.CreateMemberRequestDto;
import com.flab.demo.exception.member.DuplicatedMemberException;
import com.flab.demo.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;

    public Member join(CreateMemberRequestDto createMemberRequestDto) {
        if(memberMapper.getByEmail(createMemberRequestDto.getEmail()) != null) {
            throw new DuplicatedMemberException();
        }

        Member member = createMemberRequestDto.toEntity();
        memberMapper.create(member);
        return member;
    }

    public Member getById(String id) {
        return memberMapper.getById(id);
    }

    public Member getByEmail(String email) { return memberMapper.getByEmail(email); }
}

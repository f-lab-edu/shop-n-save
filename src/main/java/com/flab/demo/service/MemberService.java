package com.flab.demo.service;

import com.flab.demo.domain.Member;
import com.flab.demo.dto.member.CreateMemberRequestDto;
import com.flab.demo.dto.member.ModifyMemberRequestDto;
import com.flab.demo.exception.member.DuplicatedMemberException;
import com.flab.demo.exception.member.ForbiddenException;
import com.flab.demo.exception.member.NotFoundMemberException;
import com.flab.demo.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;

    public Member join(CreateMemberRequestDto createMemberRequestDto) {
        memberMapper.getByEmail(createMemberRequestDto.getEmail()).ifPresent(member -> { throw new DuplicatedMemberException(); });

        Member member = createMemberRequestDto.toEntity();
        memberMapper.create(member);
        return member;
    }

    public Member getById(String id) {
        return memberMapper.getById(id).orElseThrow(NotFoundMemberException::new);
    }

    public Member getByEmail(String email) {
        return memberMapper.getByEmail(email).orElseThrow(NotFoundMemberException::new);
    }

    public void modifyMember(String id, ModifyMemberRequestDto modifyMemberRequestDto) {
        Member member = memberMapper.getById(id).orElseThrow(NotFoundMemberException::new);
        if(!StringUtils.equals(String.valueOf(member.getId()), id)) {
            throw new ForbiddenException();
        }
        memberMapper.modifyById(id, modifyMemberRequestDto.toEntity());
    }
}

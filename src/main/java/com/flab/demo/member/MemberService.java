package com.flab.demo.member;

import com.flab.demo.member.domain.Member;
import com.flab.demo.member.dto.CreateMemberRequestDto;
import com.flab.demo.member.dto.UpdateMemberRequestDto;
import com.flab.demo.member.exception.DuplicatedMemberException;
import com.flab.demo.member.exception.NotFoundMemberException;
import com.flab.demo.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;

    public void join(CreateMemberRequestDto createMemberRequestDto) {
        memberMapper.getByEmail(createMemberRequestDto.getEmail()).ifPresent(member -> {
            throw new DuplicatedMemberException();
        });

        Member member = createMemberRequestDto.toEntity();
        memberMapper.create(member);
    }

    public Member getById(long id) {
        return memberMapper.getById(id).orElseThrow(NotFoundMemberException::new);
    }

    public Member getByEmail(String email) {
        return memberMapper.getByEmail(email).orElseThrow(NotFoundMemberException::new);
    }

    public void updateMember(long id, UpdateMemberRequestDto updateMemberRequestDto) {
        Member member = memberMapper.getById(id).orElseThrow(NotFoundMemberException::new);

        updateMemberRequestDto.getUpdatableName().ifPresent(member::changeName);
        updateMemberRequestDto.getUpdatablePassword().ifPresent(member::changePassword);
        updateMemberRequestDto.getUpdatableAddress().ifPresent(member::changeAddress);
        memberMapper.updateMember(member);
    }
}

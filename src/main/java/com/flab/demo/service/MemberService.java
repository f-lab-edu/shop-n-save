package com.flab.demo.service;

import com.flab.demo.domain.Member;
import com.flab.demo.repository.MemberRepository;

import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member member) {
        memberRepository.findByEmail(member.getEmail()).ifPresent(m -> {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        });

        memberRepository.save(member);
        return member.getId();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}

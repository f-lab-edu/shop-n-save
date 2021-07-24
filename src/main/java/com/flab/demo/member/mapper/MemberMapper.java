package com.flab.demo.member.mapper;

import com.flab.demo.member.domain.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface MemberMapper {

    int create(Member member);

    Optional<Member> getById(long id);

    Optional<Member> getByEmail(String email);

    void updateMember(Member member);
}
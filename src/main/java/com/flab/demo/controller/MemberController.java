package com.flab.demo.controller;

import com.flab.demo.domain.Member;
import com.flab.demo.dto.member.CreateMemberRequestDto;
import com.flab.demo.dto.member.LoginMemberRequestDto;
import com.flab.demo.dto.member.ModifyMemberRequestDto;
import com.flab.demo.enums.Role;
import com.flab.demo.interceptor.Authority;
import com.flab.demo.interceptor.SelfAuthorization;
import com.flab.demo.service.MemberService;
import com.flab.demo.system.Authentification;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final Authentification authentification;

    @PostMapping("/members")
    @ResponseStatus(HttpStatus.CREATED)
    public void join(@Valid @RequestBody CreateMemberRequestDto createMemberRequestDto) {
        memberService.join(createMemberRequestDto);
    }

    @Authority(target = {Role.BASIC_MEMBER})
    @SelfAuthorization
    @GetMapping("/members/{id}")
    public Member getById(@PathVariable("id") String id) {
        return memberService.getById(id);
    }

    @PostMapping("/members/login")
    public void login(@Valid @RequestBody LoginMemberRequestDto loginMemberRequestDto) {
        authentification.login(loginMemberRequestDto);
    }

    @Authority(target = {Role.BASIC_MEMBER})
    @SelfAuthorization
    @PutMapping("/members/{id}")
    public void modifyMember(@PathVariable("id") Long id, @Valid @RequestBody ModifyMemberRequestDto modifyMemberRequestDto) {
        memberService.modifyMember(id.toString(), modifyMemberRequestDto);
    }
}

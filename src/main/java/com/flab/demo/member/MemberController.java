package com.flab.demo.member;

import com.flab.demo.annotation.Authority;
import com.flab.demo.annotation.LoginMember;
import com.flab.demo.enums.Role;
import com.flab.demo.member.domain.AuthMember;
import com.flab.demo.member.domain.Member;
import com.flab.demo.member.dto.CreateMemberRequestDto;
import com.flab.demo.member.dto.LoginMemberRequestDto;
import com.flab.demo.member.dto.UpdateMemberRequestDto;
import com.flab.demo.member.exception.ForbiddenException;
import com.flab.demo.system.Authentication;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final Authentication authentication;

    @PostMapping("/members")
    @ResponseStatus(HttpStatus.CREATED)
    public void join(@Valid @RequestBody final CreateMemberRequestDto createMemberRequestDto) {
        memberService.join(createMemberRequestDto);
    }

    @Authority(target = {Role.BASIC_MEMBER})
    @GetMapping("/members/{id}")
    public Member getById(@PathVariable("id") final long id) {
        return memberService.getById(id);
    }

    @PostMapping("/members/login")
    public void login(@Valid @RequestBody final LoginMemberRequestDto loginMemberRequestDto) {
        authentication.login(loginMemberRequestDto);
    }

    @Authority(target = {Role.BASIC_MEMBER})
    @PutMapping("/members/{id}")
    public void updateMember(@PathVariable("id") final long id, @Valid @RequestBody final UpdateMemberRequestDto updateMemberRequestDto, @LoginMember AuthMember authMember) {
        if ((authMember.getRole() != Role.ADMIN) && !authMember.getId().equals(id)) {
            throw new ForbiddenException();
        }
        memberService.updateMember(id, updateMemberRequestDto);
    }

    @DeleteMapping("/members/logout")
    public void logout() {
        authentication.logout();
    }
}

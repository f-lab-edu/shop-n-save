package com.flab.demo.controller;

import com.flab.demo.domain.Member;
import com.flab.demo.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members")
    public Member create(@Valid @RequestBody Member member) {
        return memberService.create(member);
    }

    @GetMapping("/members/{id}")
    public Member getById(@PathVariable("id") String id) {
        return memberService.getById(id);
    }

    @PostMapping("/members/login")
    public void login(@Valid @RequestBody Member member, HttpSession session) {
        memberService.login(member, session);
    }
}

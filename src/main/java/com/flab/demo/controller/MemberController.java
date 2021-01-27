package com.flab.demo.controller;

import com.flab.demo.domain.Member;
import com.flab.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @ResponseBody
    @PostMapping("/members/new")
    public Long create(@RequestBody Member member) {
        Long memberId = memberService.join(member);
        return memberId;
    }

}

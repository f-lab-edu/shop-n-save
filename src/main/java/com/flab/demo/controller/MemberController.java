package com.flab.demo.controller;

import com.flab.demo.domain.Member;
import com.flab.demo.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members")
    public ResponseEntity<Member> create(@Valid @RequestBody Member member) {
        return new ResponseEntity<Member>(memberService.create(member), HttpStatus.OK);
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<Member> getById(@PathVariable("id") String id) {
        return new ResponseEntity<Member>(memberService.getById(id), HttpStatus.OK);
    }
}

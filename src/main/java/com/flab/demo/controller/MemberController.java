package com.flab.demo.controller;

import com.flab.demo.domain.Member;
import com.flab.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("")
    public ResponseEntity<Member> create(@RequestBody Member member) {
        try {
            return new ResponseEntity<Member>(memberService.create(member), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<Member>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getById(@PathVariable("id") String id) {
        try {
            return new ResponseEntity<Member>(memberService.getById(id), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<Member>(HttpStatus.BAD_REQUEST);
        }
    }
}

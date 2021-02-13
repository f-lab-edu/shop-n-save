package com.flab.demo.controller;

import com.flab.demo.domain.Member;
import com.flab.demo.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members")
    public ResponseEntity<Member> create(@RequestBody Member member) {
        try {
            return new ResponseEntity<Member>(memberService.create(member), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<Member>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<Member> getById(@PathVariable("id") String id) {
        try {
            return new ResponseEntity<Member>(memberService.getById(id), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<Member>(HttpStatus.BAD_REQUEST);
        }
    }
}

package com.flab.demo.controller;

import com.flab.demo.domain.Member;
import com.flab.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        ResponseEntity<Member> entity = null;
        try {
            entity = new ResponseEntity<Member>(memberService.create(member), HttpStatus.OK);
        } catch(Exception e) {
            entity = new ResponseEntity<Member>(HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    @GetMapping("")
    public ResponseEntity<List<Member>> getAll() {
        ResponseEntity<List<Member>> entity = null;
        try {
            entity = new ResponseEntity<>(memberService.getAll(), HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getById(@PathVariable("id") String id) {
        ResponseEntity<Member> entity = null;
        try {
            entity = new ResponseEntity<Member>(memberService.getById(id), HttpStatus.OK);
        } catch(Exception e) {
            entity = new ResponseEntity<Member>(HttpStatus.BAD_REQUEST);
        }
        return entity;
    }
}

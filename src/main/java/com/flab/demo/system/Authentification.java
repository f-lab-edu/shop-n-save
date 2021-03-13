package com.flab.demo.system;

import com.flab.demo.domain.Member;

import javax.servlet.http.HttpSession;

public interface Authentification {

    public Member login(Member member, HttpSession session);
}

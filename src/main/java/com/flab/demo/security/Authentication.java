package com.flab.demo.security;

import javax.servlet.http.HttpSession;

public interface Authentication {

    void login(HttpSession session, Long loginUserId);
}

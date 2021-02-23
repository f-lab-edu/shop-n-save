package com.flab.demo.security;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class HttpSessionUtil implements Authentication {
    static final String LOGIN_USER_ID_SESSION_KEY = "loginUserId";

    @Override
    public void login(HttpSession session, Long loginUserId) {
        session.setAttribute(LOGIN_USER_ID_SESSION_KEY, loginUserId);
    }
}

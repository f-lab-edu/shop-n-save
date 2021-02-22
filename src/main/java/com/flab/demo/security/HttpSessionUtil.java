package com.flab.demo.security;

import javax.servlet.http.HttpSession;

public class HttpSessionUtil {
    static final String LOGIN_USER_ID_SESSION_KEY = "loginUserId";

    public static void addUserSession(HttpSession session, Long loginUserId) {
        session.setAttribute(LOGIN_USER_ID_SESSION_KEY, loginUserId);
    }
}

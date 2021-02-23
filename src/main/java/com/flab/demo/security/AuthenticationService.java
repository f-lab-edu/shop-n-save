package com.flab.demo.security;

import javax.servlet.http.HttpSession;

public interface AuthenticationService {

    void login(HttpSession session, Long loginUserId);
}

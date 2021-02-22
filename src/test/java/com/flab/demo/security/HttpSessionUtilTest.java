package com.flab.demo.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;

import static com.flab.demo.security.HttpSessionUtil.LOGIN_USER_ID_SESSION_KEY;
import static org.assertj.core.api.Assertions.assertThat;

class HttpSessionUtilTest {
    @Test
    @DisplayName("사용자가 로그인 성공 시 해당 ID 를 session 에 저장을 저장한다.")
    void addUserSession_test() {
        //given
        long loginUserId = 1L;
        MockHttpSession request = new MockHttpSession();

        //when
        HttpSessionUtil.addUserSession(request, loginUserId);

        //then
        assertThat(request.getAttribute(LOGIN_USER_ID_SESSION_KEY)).isEqualTo(loginUserId);
    }
}

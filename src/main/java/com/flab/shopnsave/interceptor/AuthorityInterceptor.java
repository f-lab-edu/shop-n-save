package com.flab.shopnsave.interceptor;

import com.flab.shopnsave.annotation.Authority;
import com.flab.shopnsave.enums.Role;
import com.flab.shopnsave.member.domain.AuthMember;
import com.flab.shopnsave.member.exception.ForbiddenException;
import com.flab.shopnsave.member.exception.UnAuthorizedException;
import com.flab.shopnsave.system.Authentication;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class AuthorityInterceptor implements HandlerInterceptor {

    private final Authentication authentication;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)) return true;
        Authority auth = ((HandlerMethod) handler).getMethodAnnotation(Authority.class);

        if (auth == null) return true;

        //  로그인 유무를 확인합니다.
        AuthMember authMember = authentication.getLoginMember().orElseThrow(UnAuthorizedException::new);

        /*
            - ADMIN 권한을 가진 사용자는 모든 API를 호출할 수 있습니다.
            - 로그인한 사용자라면 BASIC_MEMBER 권한이 필요한 API를 호출할 수 있습니다.
         */
        if (authMember.getRole() == Role.ADMIN) return true;
        if (ArrayUtils.contains(auth.target(), Role.BASIC_MEMBER)) return true;

        if (!ArrayUtils.contains(auth.target(), authMember.getRole())) {
            throw new ForbiddenException();
        }

        return true;
    }
}
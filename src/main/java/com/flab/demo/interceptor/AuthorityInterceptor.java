package com.flab.demo.interceptor;

import com.flab.demo.domain.Member;
import com.flab.demo.enums.Role;
import com.flab.demo.exception.member.ForbiddenException;
import com.flab.demo.exception.member.UnAuthorizedException;
import com.flab.demo.service.MemberService;
import com.flab.demo.system.Authentification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuthorityInterceptor implements HandlerInterceptor {

    private final MemberService memberService;
    private final Authentification authentification;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(!(handler instanceof HandlerMethod)) return true;
        Authority auth = ((HandlerMethod) handler).getMethodAnnotation(Authority.class);
        SelfAuthorization selfAuth = ((HandlerMethod) handler).getMethodAnnotation(SelfAuthorization.class);

        if(auth == null) return true;

        //  로그인 유무를 확인합니다.
        String memberEmail = authentification.getLoginMemberEmail();
        if(memberEmail == null) throw new UnAuthorizedException();
        Member member = memberService.getByEmail(memberEmail);

        /*
            - ADMIN 권한을 가진 사용자는 모든 API를 호출할 수 있습니다.
            - 본인 권한이 필요한 API는 ADMIN, 본인만 호출할 수 있습니다.
            - 로그인한 사용자라면 BASIC_MEMBER 권한이 필요한 API를 호출할 수 있습니다.
         */
        if(member.getRole() == Role.ADMIN) return true;
        if(selfAuth != null) selfAuthenticationCheck(request, member);
        if(ArrayUtils.contains(auth.target(), Role.BASIC_MEMBER)) return true;

        if(!ArrayUtils.contains(auth.target(), member.getRole())) {
            throw new ForbiddenException();
        }

        return true;
    }

    private void selfAuthenticationCheck(HttpServletRequest request, Member member) {
        Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Long memberId = Long.valueOf((String) pathVariables.get("id"));

        if(!memberId.equals(member.getId())) throw new ForbiddenException();
    }
}
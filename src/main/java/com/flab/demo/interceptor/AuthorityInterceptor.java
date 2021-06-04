package com.flab.demo.interceptor;

import com.flab.demo.domain.Member;
import com.flab.demo.exception.member.UnAuthorizedException;
import com.flab.demo.exception.member.UnknownRoleValueException;
import com.flab.demo.exception.member.ImproperAuthorityException;
import com.flab.demo.service.MemberService;
import com.flab.demo.system.Authentification;
import lombok.RequiredArgsConstructor;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.flab.demo.enums.Role.*;

@RequiredArgsConstructor
public class AuthorityInterceptor implements HandlerInterceptor {
    private final MemberService memberService;
    private final Authentification authentification;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!(handler instanceof HandlerInterceptor)) return true;

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Authority auth = handlerMethod.getMethodAnnotation(Authority.class);

        if(auth == null) return true;

        String memberEmail = authentification.getLoginMemberEmail();
        if(memberEmail == null) throw new UnAuthorizedException();
        Member member = memberService.getByEmail(memberEmail);

        switch (auth.target()) {
            case BASIC_MEMBER :
                break;
            case SELLER :
                if(member.getRole() != SELLER) throw new ImproperAuthorityException();
                break;
            case ADMIN :
                if(member.getRole() != ADMIN) throw new ImproperAuthorityException();
                break;
            default:
                throw new UnknownRoleValueException(auth.target().getCode());
        }
        return true;
    }
}
package com.flab.demo.exception.member;

import com.flab.demo.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class UnAuthorizedException extends BusinessException {

    public UnAuthorizedException() {
        super("인증되지 않은 사용자입니다.", HttpStatus.UNAUTHORIZED);
    }
}

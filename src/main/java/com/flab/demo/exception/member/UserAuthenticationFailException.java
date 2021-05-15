package com.flab.demo.exception.member;

import com.flab.demo.exception.BusinessException;
import com.flab.demo.exception.ErrorCode;

public class UserAuthenticationFailException extends BusinessException {

    public UserAuthenticationFailException(ErrorCode errorCode) {
        super(errorCode);
    }
}

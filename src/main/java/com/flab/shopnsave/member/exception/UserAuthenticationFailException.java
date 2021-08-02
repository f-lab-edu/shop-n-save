package com.flab.shopnsave.member.exception;

import com.flab.shopnsave.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class UserAuthenticationFailException extends BusinessException {

    public UserAuthenticationFailException() {
        super("아이디가 존재하지 않거나 비밀번호가 틀립니다.", HttpStatus.BAD_REQUEST);
    }
}

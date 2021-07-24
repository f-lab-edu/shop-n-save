package com.flab.demo.member.exception;

import com.flab.demo.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class UnknownRoleValueException extends BusinessException {

    public UnknownRoleValueException(int value) {
        super("알 수 없는 권한입니다. Role Value " + value, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

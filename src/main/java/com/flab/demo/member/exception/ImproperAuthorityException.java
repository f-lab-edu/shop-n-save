package com.flab.demo.member.exception;

import com.flab.demo.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class ImproperAuthorityException extends BusinessException {

    public ImproperAuthorityException() {
        super("권한이 없는 사용자입니다.", HttpStatus.FORBIDDEN);
    }
}

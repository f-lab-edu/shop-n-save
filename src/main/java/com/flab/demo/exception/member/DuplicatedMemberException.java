package com.flab.demo.exception.member;

import com.flab.demo.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class DuplicatedMemberException extends BusinessException {

    public DuplicatedMemberException() {
        super("이미 존재하는 회원입니다.", HttpStatus.CONFLICT);
    }
}

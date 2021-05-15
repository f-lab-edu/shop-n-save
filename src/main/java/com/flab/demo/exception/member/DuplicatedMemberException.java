package com.flab.demo.exception.member;

import com.flab.demo.exception.BusinessException;
import com.flab.demo.exception.ErrorCode;

public class DuplicatedMemberException extends BusinessException {

    public DuplicatedMemberException(ErrorCode errorCode) {
        super(errorCode);
    }
}

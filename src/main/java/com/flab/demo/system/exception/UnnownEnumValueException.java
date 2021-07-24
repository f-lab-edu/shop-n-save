package com.flab.demo.system.exception;

import com.flab.demo.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class UnnownEnumValueException extends BusinessException {

    public UnnownEnumValueException(String name, int code) {
        super("알 수 없는 형식의 값을 갖습니다.(" + name + ":" + code + ")", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

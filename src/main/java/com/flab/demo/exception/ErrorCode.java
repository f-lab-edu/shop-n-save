package com.flab.demo.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "C001", "잘못된 입력입니다."),

    EMAIL_DUPLICATION(HttpStatus.BAD_REQUEST, "M001", "이미 존재하는 회원입니다."),
    LOGIN_INPUT_INVALID(HttpStatus.UNAUTHORIZED, "M002", "아이디가 존재하지 않거나 비밀번호가 틀립니다.");

    public final HttpStatus statusCode;
    public final String code;
    public final String message;
}
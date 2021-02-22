package com.flab.demo.advice;

import com.flab.demo.exception.NotAuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.flab.demo.controller.ResponseEntityGroup.INTERNAL_SERVER_ERROR_RESPONSE_ENTITY;

@Slf4j
@ControllerAdvice
public class ErrorExceptionControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleMethodException(Exception e) {
        log.error("[Exception] Server Internal Error : ", e);

        return INTERNAL_SERVER_ERROR_RESPONSE_ENTITY.getEntity();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();

        return ResponseEntity.badRequest().body(errorMessage);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleRuntimeException(RuntimeException e) {
        return ResponseEntity.unprocessableEntity().body(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.unprocessableEntity().body(e.getMessage());
    }

    @ExceptionHandler(NotAuthenticationException.class)
    public ResponseEntity handleNotAuthenticationException(NotAuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
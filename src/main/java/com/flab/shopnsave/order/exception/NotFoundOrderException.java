package com.flab.shopnsave.order.exception;

import com.flab.shopnsave.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class NotFoundOrderException extends BusinessException {

    public NotFoundOrderException() {
        super("주문을 찾을 수 없습니다.", HttpStatus.BAD_REQUEST);
    }
}
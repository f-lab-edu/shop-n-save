package com.flab.shopnsave.order.exception;

import com.flab.shopnsave.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class UnknownOrderStatusValueException extends BusinessException {

    public UnknownOrderStatusValueException(int value) {
        super("알 수 없는 배송 상태 입니다. OrderStatusValue " + value, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
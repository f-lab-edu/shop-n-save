package com.flab.shopnsave.payment.exception;

import com.flab.shopnsave.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class NonPayableOrderStatus extends BusinessException {

    public NonPayableOrderStatus(int value) {
        super("결제 불가능한 주문 상태입니다. OrderStatusValue " + value, HttpStatus.BAD_REQUEST);
    }
}

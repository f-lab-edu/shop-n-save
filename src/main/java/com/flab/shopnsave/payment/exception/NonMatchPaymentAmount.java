package com.flab.shopnsave.payment.exception;

import com.flab.shopnsave.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class NonMatchPaymentAmount extends BusinessException {

    public NonMatchPaymentAmount(int requestPrice, int totalPrice) {
        super("결제 금액을 확인해주세요. (" + requestPrice + " is not match with " + totalPrice + ")", HttpStatus.BAD_REQUEST);
    }
}
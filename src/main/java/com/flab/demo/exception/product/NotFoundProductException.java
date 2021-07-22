package com.flab.demo.exception.product;

import com.flab.demo.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class NotFoundProductException extends BusinessException {

    public NotFoundProductException() {
        super("상품을 찾을 수 없습니다.", HttpStatus.BAD_REQUEST);
    }
}

package com.flab.shopnsave.category.exception;

import com.flab.shopnsave.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class NotFoundCategoryException extends BusinessException {
    public NotFoundCategoryException() {
        super("카테고리를 찾을 수 없습니다.", HttpStatus.BAD_REQUEST);
    }
}

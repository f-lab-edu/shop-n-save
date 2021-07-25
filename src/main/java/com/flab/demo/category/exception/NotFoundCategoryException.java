<<<<<<< HEAD:src/main/java/com/flab/shopnsave/category/exception/NotFoundCategoryException.java
package com.flab.shopnsave.category.exception;
=======
package com.flab.demo.category.exception;
>>>>>>> refactor: 패키지 구조 변경, import 정리, convention 정리:src/main/java/com/flab/demo/category/exception/NotFoundCategoryException.java

import com.flab.shopnsave.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class NotFoundCategoryException extends BusinessException {
    public NotFoundCategoryException() {
        super("카테고리를 찾을 수 없습니다.", HttpStatus.BAD_REQUEST);
    }
}
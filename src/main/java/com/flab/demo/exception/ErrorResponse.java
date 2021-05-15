package com.flab.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ErrorResponse {

    private String code;
    private String message;
    private List<ErrorField> errors;

    public ErrorResponse(ErrorCode code, List<ErrorField> errors) {
        this.code = code.getCode();
        this.message = code.getMessage();
        this.errors = errors;
    }

    public ErrorResponse(ErrorCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    public static ErrorResponse of(ErrorCode code, BindingResult bindingResult) {
        return new ErrorResponse(code, ErrorField.of(bindingResult));
    }

    public static ErrorResponse of(ErrorCode code) {
        return new ErrorResponse(code);
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ErrorField {
        private String field;
        private String value;
        private String reason;

        public static List<ErrorField> of(BindingResult bindingResult) {
            List<ErrorField> errorFields = bindingResult.getAllErrors().stream().map(error ->
                    new ErrorField(((FieldError) error).getField(), ((FieldError) error).getRejectedValue().toString(),
                            ((FieldError) error).getDefaultMessage())).collect(Collectors.toList());

            return errorFields;
        }
    }
}

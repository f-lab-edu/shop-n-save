package com.flab.demo.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ValidationError {
    Map<String, String> errors = new HashMap<>();
}

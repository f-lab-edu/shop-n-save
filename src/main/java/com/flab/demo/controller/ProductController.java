package com.flab.demo.controller;

import com.flab.demo.dto.product.CreateProductRequestDto;
import com.flab.demo.enums.Role;
import com.flab.demo.interceptor.Authority;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ProductController {

    @Authority(target = {Role.SELLER})
    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@Valid @RequestBody CreateProductRequestDto createProductRequestDto) {

    }
}

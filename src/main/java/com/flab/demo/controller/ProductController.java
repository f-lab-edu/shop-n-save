package com.flab.demo.controller;

import com.flab.demo.annotation.LoginMember;
import com.flab.demo.domain.AuthMember;
import com.flab.demo.dto.product.CreateProductRequestDto;
import com.flab.demo.enums.Role;
import com.flab.demo.annotation.Authority;
import com.flab.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Authority(target = {Role.SELLER})
    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@Valid @RequestBody CreateProductRequestDto createProductRequestDto, @LoginMember AuthMember member) {
        productService.createProduct(createProductRequestDto, member.getId().toString());
    }
}

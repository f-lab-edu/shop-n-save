package com.flab.demo.controller;

import com.flab.demo.annotation.LoginMember;
import com.flab.demo.domain.AuthMember;
import com.flab.demo.domain.Product;
import com.flab.demo.dto.product.CreateProductRequestDto;
import com.flab.demo.dto.product.ModifyProductRequestDto;
import com.flab.demo.enums.Role;
import com.flab.demo.annotation.Authority;
import com.flab.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Authority(target = {Role.SELLER})
    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@Valid @RequestBody final CreateProductRequestDto createProductRequestDto, @LoginMember AuthMember member) {
        productService.createProduct(createProductRequestDto, member.getId());
    }

    @GetMapping("/products/{id}")
    public Product getById(@PathVariable("id") final long id) {
        return productService.getById(id);
    }

    @Authority(target = {Role.SELLER})
    @PutMapping("/products/{id}")
    public void modifyProduct(@PathVariable("id") final long id, @Valid @RequestBody final ModifyProductRequestDto modifyProductRequestDto, @LoginMember AuthMember authMember) {
        productService.modifyProduct(id, modifyProductRequestDto, authMember);
    }
}

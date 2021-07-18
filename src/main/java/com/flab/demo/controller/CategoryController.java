package com.flab.demo.controller;

import com.flab.demo.dto.category.CategoryRequestDto;
import com.flab.demo.enums.Role;
import com.flab.demo.interceptor.Authority;
import com.flab.demo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @Authority(target = {Role.ADMIN})
    @PostMapping("/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@Valid @RequestBody CategoryRequestDto categoryRequestDto) {
        categoryService.register(categoryRequestDto);
    }
}

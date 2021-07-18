package com.flab.shopnsave.category;

import com.flab.shopnsave.annotation.Authority;
import com.flab.shopnsave.category.dto.CategoryRequestDto;
import com.flab.shopnsave.enums.Role;
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

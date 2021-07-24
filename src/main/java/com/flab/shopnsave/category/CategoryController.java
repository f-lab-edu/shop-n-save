package com.flab.shopnsave.category;

import com.flab.shopnsave.annotation.Authority;
import com.flab.shopnsave.category.domain.Category;
import com.flab.shopnsave.category.dto.CategoryRequestDto;
import com.flab.shopnsave.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @Authority(target = {Role.ADMIN})
    @PostMapping("/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerCategory(@Valid @RequestBody final CategoryRequestDto categoryRequestDto) {
        categoryService.registerCategory(categoryRequestDto);
    }

    @GetMapping("/categories/{id}")
    public Category getById(@PathVariable("id") final int id) {
        return categoryService.getById(id);
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @Authority(target = {Role.ADMIN})
    @PutMapping("/categories/{id}")
    public void updateCategory(@PathVariable("id") int id, @Valid @RequestBody final CategoryRequestDto categoryRequestDto) {
        categoryService.updateCategory(id, categoryRequestDto);
    }
}

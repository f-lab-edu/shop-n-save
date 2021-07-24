package com.flab.demo.service;

import com.flab.demo.domain.Category;
import com.flab.demo.dto.category.CategoryRequestDto;
import com.flab.demo.exception.category.NotFoundCategoryException;
import com.flab.demo.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryMapper categoryMapper;

    public void registerCategory(CategoryRequestDto categoryRequestDto) {
        categoryMapper.registerCategory(categoryRequestDto.toEntity());
    }

    public Category getById(Integer id) {
        return categoryMapper.getById(id).orElseThrow(NotFoundCategoryException::new);
    }

    public List<Category> getAllCategories() {
        return categoryMapper.getAllCategories();
    }

    public void updateCategory(Integer id, CategoryRequestDto categoryRequestDto) {
        Category category = getById(id);
        categoryRequestDto.getUpdatableCategoryName().ifPresent(category::changeCategoryName);
        categoryRequestDto.getUpdatableParentId().ifPresent(category::changeParentId);
        categoryMapper.updateCategory(category);
    }
}

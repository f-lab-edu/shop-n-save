package com.flab.demo.category;

import com.flab.demo.category.domain.Category;
import com.flab.demo.category.dto.CreateCategoryRequestDto;
import com.flab.demo.category.dto.UpdateCategoryRequestDto;
import com.flab.demo.category.exception.NotFoundCategoryException;
import com.flab.demo.category.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryMapper categoryMapper;

    public void registerCategory(CreateCategoryRequestDto categoryRequestDto) {
        categoryMapper.registerCategory(categoryRequestDto.toEntity());
    }

    public Category getById(Integer id) {
        return categoryMapper.getById(id).orElseThrow(NotFoundCategoryException::new);
    }

    @Cacheable(value="category", unless="#result == null")
    public List<Category> getAllCategories() {
        return categoryMapper.getAllCategories();
    }

    @CacheEvict(value="category")
    public void updateCategory(Integer id, UpdateCategoryRequestDto categoryRequestDto) {
        Category category = getById(id);
        categoryRequestDto.getUpdatableCategoryName().ifPresent(category::changeCategoryName);
        categoryRequestDto.getUpdatableParentId().ifPresent(category::changeParentId);
        categoryMapper.updateCategory(category);
    }
}

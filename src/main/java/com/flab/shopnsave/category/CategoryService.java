package com.flab.shopnsave.category;

import com.flab.shopnsave.category.domain.Category;
import com.flab.shopnsave.category.dto.CreateCategoryRequestDto;
import com.flab.shopnsave.category.dto.UpdateCategoryRequestDto;
import com.flab.shopnsave.category.exception.NotFoundCategoryException;
import com.flab.shopnsave.category.mapper.CategoryMapper;
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
        if(categoryRequestDto.getParentId() == null) {
            categoryMapper.registerCategory(categoryRequestDto.toEntity());
            return;
        }
        Category parentCategory = getById(categoryRequestDto.getParentId());
        categoryMapper.registerCategory(categoryRequestDto.toEntityWithDepth(parentCategory.getDepth() + 1));
    }

    public Category getById(int id) {
        return categoryMapper.getById(id).orElseThrow(NotFoundCategoryException::new);
    }

    @Cacheable(value="category", key = "#depth", unless="#result == null")
    public List<Category> getAllCategories(int depth) {
        return categoryMapper.getAllCategories(depth);
    }

    @CacheEvict(value="category")
    public void updateCategory(int id, UpdateCategoryRequestDto categoryRequestDto) {
        Category category = getById(id);
        categoryRequestDto.getUpdatableCategoryName().ifPresent(category::changeCategoryName);
        categoryRequestDto.getUpdatableParentId().ifPresent(category::changeParentId);
        categoryMapper.updateCategory(category);
    }
}

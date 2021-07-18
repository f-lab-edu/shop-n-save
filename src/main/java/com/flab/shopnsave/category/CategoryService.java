package com.flab.shopnsave.category;

import com.flab.shopnsave.category.dto.CategoryRequestDto;
import com.flab.shopnsave.category.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryMapper categoryMapper;

    public void register(CategoryRequestDto categoryRequestDto) {
        categoryMapper.register(categoryRequestDto.toEntity());
    }
}

package com.flab.demo.service;

import com.flab.demo.dto.category.CategoryRequestDto;
import com.flab.demo.mapper.CategoryMapper;
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

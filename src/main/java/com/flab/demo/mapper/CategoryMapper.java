package com.flab.demo.mapper;

import com.flab.demo.domain.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CategoryMapper {

    void registerCategory(Category category);

    Optional<Category> getById(int id);

    List<Category> getAllCategories();

    void updateCategory(Category category);
}

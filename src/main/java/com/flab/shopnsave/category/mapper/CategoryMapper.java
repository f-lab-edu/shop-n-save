package com.flab.shopnsave.category.mapper;

import com.flab.shopnsave.category.domain.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CategoryMapper {

    void registerCategory(Category category);

    Optional<Category> getById(int id);

    List<Category> getAllCategories(int depth);

    void updateCategory(Category category);
}

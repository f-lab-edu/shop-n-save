package com.flab.demo.mapper;

import com.flab.demo.domain.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface CategoryMapper {

    int register(Category category);

    Optional<Category> getById(int id);

    void modifyProduct(Category category);
}

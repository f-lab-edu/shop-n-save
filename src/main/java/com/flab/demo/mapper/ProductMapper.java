package com.flab.demo.mapper;

import com.flab.demo.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.Optional;

@Mapper
public interface ProductMapper {

    int create(Product product);

    Optional<Product> getById(Long id);

    void modifyProduct(Product product);
}

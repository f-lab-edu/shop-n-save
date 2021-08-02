package com.flab.shopnsave.mapper;

import com.flab.shopnsave.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.Optional;

@Mapper
public interface ProductMapper {

    int create(Product product);

    Optional<Product> getById(long id);

    void modifyProduct(Product product);
}

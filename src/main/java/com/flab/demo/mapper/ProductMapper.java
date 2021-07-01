package com.flab.demo.mapper;

import com.flab.demo.domain.Product;
import com.sql.ProductSQL;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ProductMapper {

    @InsertProvider(type = ProductSQL.class, method = "create")
    @Options(useGeneratedKeys = true, keyProperty = "product.id")
    int create(@Param("product") Product product, @Param("sellerId") String sellerId);
}

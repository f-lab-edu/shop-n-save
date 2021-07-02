package com.flab.demo.mapper;

import com.flab.demo.domain.Product;
import com.sql.ProductSQL;
import org.apache.ibatis.annotations.*;

import java.util.Optional;

@Mapper
public interface ProductMapper {

    @InsertProvider(type = ProductSQL.class, method = "create")
    @Options(useGeneratedKeys = true, keyProperty = "product.id")
    int create(@Param("product") Product product, @Param("sellerId") String sellerId);

    @SelectProvider(type = ProductSQL.class, method = "getById")
    Optional<Product> getById(@Param("id") String id);

    @UpdateProvider(type = ProductSQL.class, method = "modifyProduct")
    void modifyProduct(@Param("id") String id, @Param("product") Product product);
}

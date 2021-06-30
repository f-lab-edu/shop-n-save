package com.flab.demo.mapper;

import com.flab.demo.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProductMapper {

    @Insert("INSERT INTO PRODUCT(product_name, fixed_price, quantity, seller_id) " +
            "VALUES(#{product.productName}, #{product.fixedPrice}, #{product.quantity}, #{sellerId})")
    @Options(useGeneratedKeys = true, keyProperty = "product.id")
    int create(@Param("product") Product product, String sellerId);
}

package com.sql

import com.flab.demo.domain.Product
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.jdbc.SQL

class ProductSQL {

    public String create(@Param("product") Product product, @Param("sellerId") String sellerId) {
        return new SQL() {{
            INSERT_INTO("PRODUCT")
            VALUES("product_name, fixed_price, quantity, seller_id",
                    "#{product.productName}, #{product.fixedPrice}, #{product.quantity}, #{sellerId}")
        }}
    }

    public String getById(@Param("id") String id) {
        return new SQL() {{
            SELECT("*")
            FROM("PRODUCT")
            WHERE("id=#{id}")
        }}
    }

    public String modifyProduct(@Param("id") String id, @Param("product") Product product) {
        return new SQL() {{
            UPDATE("PRODUCT")
            SET("product_name=#{product.productName}")
            SET("fixed_price=#{product.fixedPrice}")
            SET("quantity=#{product.quantity}")
            WHERE("id=#{id}")
        }}
    }
}
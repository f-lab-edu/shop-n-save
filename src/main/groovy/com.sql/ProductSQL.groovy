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
}
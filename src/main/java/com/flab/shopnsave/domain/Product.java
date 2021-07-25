package com.flab.shopnsave.domain;

import com.flab.shopnsave.dto.product.ModifyProductRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private Long id;
    private String productName;
    private int fixedPrice;
    private Long sellerId;
    private String salesYn;
    private Timestamp createDate;

    public void modifyProduct(ModifyProductRequestDto dto) {
        this.productName = dto.getProductName();
        this.fixedPrice = dto.getFixedPrice();
    }
}

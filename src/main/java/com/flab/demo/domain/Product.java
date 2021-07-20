package com.flab.demo.domain;

import com.flab.demo.dto.product.ModifyProductRequestDto;
import lombok.*;

import javax.validation.constraints.PositiveOrZero;
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

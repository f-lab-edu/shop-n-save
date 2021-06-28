package com.flab.demo.dto.product;

import com.flab.demo.domain.Member;
import com.flab.demo.domain.Product;

import javax.validation.constraints.NotBlank;

public class CreateProductRequestDto {

    @NotBlank(message = "상품명은 빈 값일 수 없습니다")
    private String productName;

    @NotBlank(message = "정가는 빈 값일 수 없습니다")
    private int fixedPrice;

    @NotBlank(message = "잔여수량은 빈 값일 수 없습니다")
    private int quantity;

    @NotBlank(message = "판매수량 빈 값일 수 없습니다")
    private int salesCount;

    public Product toEntity() {
        Product product = Product.builder()
                .productName(this.productName)
                .fixedPrice(this.fixedPrice)
                .quantity(this.quantity)
                .salesCount(this.salesCount)
                .build();
        return product;
    }
}

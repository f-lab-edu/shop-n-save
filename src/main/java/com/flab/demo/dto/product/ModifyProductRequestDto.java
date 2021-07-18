package com.flab.demo.dto.product;

import com.flab.demo.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModifyProductRequestDto {

    @NotEmpty(message = "상품명은 빈 값일 수 없습니다")
    private String productName;

    @PositiveOrZero(message = "가격은 0원 이상이어야 합니다.")
    private int fixedPrice;

    public Product toEntity(Long productId) {
        Product product = Product.builder()
                .id(productId)
                .productName(this.productName)
                .fixedPrice(this.fixedPrice)
                .build();
        return product;
    }
}
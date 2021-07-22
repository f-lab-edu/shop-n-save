package com.flab.demo.dto.product;

import com.flab.demo.domain.Product;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProductRequestDto {

    @NotBlank(message = "상품명은 빈 값일 수 없습니다")
    private String productName;

    @PositiveOrZero(message = "가격은 0원 이상이어야 합니다.")
    private int fixedPrice;

    public Product toEntity(Long sellerId) {
        Product product = Product.builder()
                .productName(this.productName)
                .fixedPrice(this.fixedPrice)
                .sellerId(sellerId)
                .build();
        return product;
    }
}

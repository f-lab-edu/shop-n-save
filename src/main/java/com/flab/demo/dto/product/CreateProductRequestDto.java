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
public class CreateProductRequestDto {

    @NotEmpty(message = "상품명은 빈 값일 수 없습니다")
    private String productName;

    @PositiveOrZero(message = "가격은 0원 이상이어야 합니다.")
    private int fixedPrice;

    @PositiveOrZero(message = "잔여수량은 0 이상이어야 합니다.")
    private int quantity;

    public Product toEntity() {
        Product product = Product.builder()
                .productName(this.productName)
                .fixedPrice(this.fixedPrice)
                .quantity(this.quantity)
                .build();
        return product;
    }
}

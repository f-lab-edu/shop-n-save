package com.flab.demo.order.dto;

import com.flab.demo.order.domain.OrderProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateOrderProductRequestDto {

    @NotBlank(message = "상품 id는 빈 값일 수 없습니다")
    private Long productId;

    @Min(value = 1, message = "주문 항목은 1개 이상이어야 합니다")
    private int count;
}

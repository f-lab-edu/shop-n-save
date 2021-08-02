package com.flab.demo.order.dto;

import com.flab.demo.order.domain.OrderProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateOrderProductRequestDto {

    @NotNull(message = "상품 id는 빈 값일 수 없습니다")
    private long productId;

    @Min(value = 1, message = "주문 수량은 1개 이상이어야 합니다")
    private int count;

    public OrderProduct toEntity(long orderId, int orderPrice) {
        OrderProduct orderProduct = OrderProduct.builder()
                .orderId(orderId)
                .productId(this.productId)
                .count(this.count)
                .orderPrice(orderPrice)
                .build();
        return orderProduct;
    }
}

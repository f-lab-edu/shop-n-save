package com.flab.shopnsave.order.dto;

import com.flab.shopnsave.member.domain.AuthMember;
import com.flab.shopnsave.order.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

import static com.flab.shopnsave.enums.OrderStatus.WAITING_PAYMENT;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateOrderRequestDto {

    @Valid
    @NotNull(message = "주문 항목은 1개 이상이어야 합니다")
    @Size(min = 1, message = "주문 항목은 1개 이상이어야 합니다")
    private List<CreateOrderProductRequestDto> orderProductRequestDtoList;

    @Valid
    @NotNull(message = "배송지 정보가 존재해야 합니다")
    private DeliveryInfoDto deliveryInfoDto;

    public Order toEntity(AuthMember authMember) {
        Order order = Order.builder()
                .status(WAITING_PAYMENT)
                .deliveryInfo(deliveryInfoDto.toEntity())
                .ordererId(authMember.getId())
                .build();
        return order;
    }
}

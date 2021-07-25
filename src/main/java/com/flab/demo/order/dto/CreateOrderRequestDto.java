package com.flab.demo.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateOrderRequestDto {

    @Valid
    @NotNull(message = "주문 항목은 1개 이상이어야 합니다")
    @Size(min = 1, message = "주문 항목은 1개 이상이어야 합니다")
    private List<CreateOrderProductRequestDto> orderProductRequestDtoList;
}

package com.flab.shopnsave.order.dto;

import com.flab.shopnsave.order.domain.DeliveryInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryInfoDto {

    @NotBlank(message = "수령자 이름은 빈 값일 수 없습니다")
    private String receiverName;

    @NotBlank(message = "주소는 빈 값일 수 없습니다")
    private String address;

    @NotBlank(message = "전화번호는 빈 값일 수 없습니다")
    private String phone;

    public DeliveryInfo toEntity() {
        DeliveryInfo deliveryInfo = DeliveryInfo.builder()
                .receiverName(receiverName)
                .address(address)
                .phone(phone)
                .build();
        return deliveryInfo;
    }
}
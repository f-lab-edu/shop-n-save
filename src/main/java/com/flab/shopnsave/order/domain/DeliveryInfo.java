package com.flab.shopnsave.order.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DeliveryInfo {

    private String receiverName;
    private String address;
    private String phone;

    @Builder
    public DeliveryInfo(@NotBlank String receiverName, @NotBlank String address, @NotBlank String phone) {
        this.receiverName = receiverName;
        this.address = address;
        this.phone = phone;
    }
}

package com.flab.shopnsave.payment.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PhonePayment extends Payment {

    private String phoneNumber;

    @Builder
    public PhonePayment(@NotBlank String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

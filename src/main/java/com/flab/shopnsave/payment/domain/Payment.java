package com.flab.shopnsave.payment.domain;

import lombok.Getter;

@Getter
public abstract class Payment {

    protected Long id;
    protected long orderId;
    protected int amount;
}

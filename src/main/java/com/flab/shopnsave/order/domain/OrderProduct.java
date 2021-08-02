package com.flab.shopnsave.order.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderProduct {

    private Long id;
    private Long orderId;
    private Long productId;
    private Integer count;
    private Integer orderPrice;
    private Timestamp createDate;

    public int getTotalPrice() {
        return count * orderPrice;
    }
}
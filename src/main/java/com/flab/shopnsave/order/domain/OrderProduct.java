package com.flab.shopnsave.order.domain;

import lombok.*;
import org.springframework.util.Assert;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderProduct {

    private Long id;
    private long orderId;
    private long productId;
    private int count;
    private int orderPrice;
    private Timestamp createDate;

    @Builder
    public OrderProduct(long orderId, long productId, int count, int orderPrice) {
        Assert.isTrue(orderId > 0, "주문 정보가 존재하지 않습니다");
        Assert.isTrue(productId > 0, "상품 정보가 존재하지 않습니다");
        Assert.isTrue(count > 0, "주문 항목은 1개 이상이어야 합니다");

        this.orderId = orderId;
        this.productId = productId;
        this.count = count;
        this.orderPrice = orderPrice;
    }

    public int getTotalPrice() {
        return count * orderPrice;
    }
}
package com.flab.shopnsave.order.domain;

import com.flab.shopnsave.enums.OrderStatus;
import com.flab.shopnsave.order.exception.UnknownOrderStatusValueException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.sql.Timestamp;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Order {

    private Long id;
    private OrderStatus status;
    private long ordererId;
    private String address;
    private List<OrderProduct> orderProductList;
    private Timestamp createdAt;

    @Builder
    public Order(OrderStatus status, long ordererId, String address, List<OrderProduct> orderProductList) {
        Assert.notNull(status, "상태가 존재하지 않습니다");
        Assert.isTrue(ordererId > 0, "주문자 정보가 존재하지 않습니다");
        Assert.hasText(address, "주소가 존재하지 않습니다");

        this.status = status;
        this.ordererId = ordererId;
        this.address = address;
        this.orderProductList = orderProductList;
    }

    private boolean isAvailableCancel() {
        switch (status) {
            case WAITING_PAYMENT:
            case PREPARING_DELIVERY:
                return true;
            case SHIPPING:
            case DELIVERY_COMPLETED:
            case CANCELED:
                return false;
            default:
                throw new UnknownOrderStatusValueException(status.getCode());
        }
    }
}
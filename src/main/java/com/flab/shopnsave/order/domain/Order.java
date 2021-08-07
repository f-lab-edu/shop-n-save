package com.flab.shopnsave.order.domain;

import com.flab.shopnsave.enums.OrderStatus;
import com.flab.shopnsave.order.exception.UnknownOrderStatusValueException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.sql.Timestamp;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Order {

    private Long id;
    private OrderStatus status;
    private long ordererId;
    private DeliveryInfo deliveryInfo;
    private List<OrderProduct> orderProductList;
    private Timestamp createdAt;

    @Builder
    public Order(@NotNull OrderStatus status, @Positive long ordererId, List<OrderProduct> orderProductList, @NotNull DeliveryInfo deliveryInfo) {
        this.status = status;
        this.ordererId = ordererId;
        this.deliveryInfo = deliveryInfo;
        this.orderProductList = orderProductList;
    }

    public boolean isAvailableCancel() {
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

    public boolean isAvailablePay() {
        switch (status) {
            case WAITING_PAYMENT:
                return true;
            case PREPARING_DELIVERY:
            case SHIPPING:
            case DELIVERY_COMPLETED:
            case CANCELED:
                return false;
            default:
                throw new UnknownOrderStatusValueException(status.getCode());
        }
    }

    public int getTotalPrice() {
        return orderProductList.stream().mapToInt(OrderProduct::getTotalPrice).sum();
    }
}
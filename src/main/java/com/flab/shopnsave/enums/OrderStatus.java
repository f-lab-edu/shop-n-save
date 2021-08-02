package com.flab.shopnsave.enums;

import com.flab.shopnsave.order.exception.UnknownOrderStatusValueException;
import org.apache.ibatis.type.MappedTypes;

public enum OrderStatus implements CodeEnum {

    WAITING_PAYMENT(1) { public boolean isShippingChangeable() { return true; } }

    , PREPARING_DELIVERY(2), SHIPPING(3), DELIVERY_COMPLETED(4), CANCELED(5);

    private final int value;

    OrderStatus(int value) {
        this.value = value;
    }

    @Override
    public int getCode() {
        return value;
    }

    public static OrderStatus valueOf(int value) {
        switch (value) {
            case 1:
                return WAITING_PAYMENT;
            case 2:
                return PREPARING_DELIVERY;
            case 3:
                return SHIPPING;
            case 4:
                return DELIVERY_COMPLETED;
            case 5:
                return CANCELED;
            default:
                throw new UnknownOrderStatusValueException(value);
        }
    }

    @MappedTypes(OrderStatus.class)
    public static class TypeHandler extends CodeEnumTypeHandler<OrderStatus> {

        public TypeHandler() {
            super(OrderStatus.class);
        }
    }
}

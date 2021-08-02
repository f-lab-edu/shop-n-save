package com.flab.demo.order.domain;

import com.flab.demo.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private Long id;
    private OrderStatus status;
    private Long ordererId;
    private String address;
    private List<OrderProduct> orderProductList;
    private Timestamp createDate;

    public void place() {

    }

    public void pay() {

    }

    public void cancel() {

    }

    public void getTotalPrice() {
        //orderProductList.stream().map(a -> a.getTotalPrice())
    }
}
package com.flab.demo.order.domain;

import com.flab.demo.domain.Member;
import com.flab.demo.domain.Product;
import com.flab.demo.order.enums.OrderStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Order {

    private Long id;
    private OrderStatus status;
    private Long ordererId;
    private List<OrderProduct> orderProductList;

    public void place() {

    }

    public void pay() {

    }

    public void cancel() {

    }

    public void getStatus() {

    }

    public void getTotalPrice() {

    }
}
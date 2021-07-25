package com.flab.demo.order.domain;

import com.flab.demo.domain.Product;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OrderProduct {

    private Long id;
    private Order order;
    private Product product;
    private int count;
}
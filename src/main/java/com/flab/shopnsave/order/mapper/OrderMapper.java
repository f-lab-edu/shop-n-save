package com.flab.shopnsave.order.mapper;

import com.flab.shopnsave.order.domain.Order;
import com.flab.shopnsave.order.domain.OrderProduct;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface OrderMapper {

    int createOrder(Order order);

    int createOrderProducts(List<OrderProduct> orderProduct);

    Optional<Order> getById(long id);

    void modifyOrder(Order order);
}


package com.internet.shop.dao;

import com.internet.shop.model.Order;
import java.util.List;
import java.util.Optional;

public interface OrderDao {
    Order create(Order order);

    Optional<Order> get(Long id);

    Order update(Order order);

    List<Order> getUserOrders(Long userId);

    boolean delete(Long id);

    List<Order> getAll();
}

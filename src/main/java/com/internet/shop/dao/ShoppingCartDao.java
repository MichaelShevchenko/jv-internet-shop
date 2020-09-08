package com.internet.shop.dao;

import com.internet.shop.model.ShoppingCart;
import java.util.List;
import java.util.Optional;

public interface ShoppingCartDao {
    ShoppingCart create(ShoppingCart shoppingCart);

    Optional<ShoppingCart> getByUserId(Long userId);

    ShoppingCart update(ShoppingCart shoppingCart);

    boolean delete(ShoppingCart shoppingCart);

    List<ShoppingCart> getAll();
}

package com.internet.shop.dao;

import com.internet.shop.db.Storage;
import com.internet.shop.lib.Dao;
import com.internet.shop.model.ShoppingCart;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Dao
public class ShoppingCartDaoImpl implements ShoppingCartDao {

    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        Storage.addShoppingCart(shoppingCart);
        return shoppingCart;
    }

    @Override
    public Optional<ShoppingCart> getByUserId(Long userId) {
        return Storage.shoppingCarts.stream()
                .filter(s -> s.getUserId().equals(userId))
                .map(ShoppingCart::clone)
                .findFirst();
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        IntStream.range(0, Storage.shoppingCarts.size())
                .filter(i -> Storage.shoppingCarts.get(i).getId().equals(shoppingCart.getId()))
                .forEach(i -> Storage.shoppingCarts.set(i, shoppingCart));
        return shoppingCart;
    }

    @Override
    public boolean delete(ShoppingCart shoppingCart) {
        return Storage.shoppingCarts.removeIf(s -> s.getId().equals(shoppingCart.getId()));
    }

    @Override
    public List<ShoppingCart> getAll() {
        return Storage.shoppingCarts.stream()
                .map(ShoppingCart::clone)
                .collect(Collectors.toList());
    }
}

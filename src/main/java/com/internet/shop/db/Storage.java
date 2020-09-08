package com.internet.shop.db;

import com.internet.shop.model.Order;
import com.internet.shop.model.Product;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.model.User;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    public static final List<Product> products = new ArrayList<>();
    public static final List<User> users = new ArrayList<>();
    public static final List<ShoppingCart> shoppingCarts = new ArrayList<>();
    public static final List<Order> orders = new ArrayList<>();
    private static Long productId = 0L;
    private static Long userId = 0L;
    private static Long shoppingCartId = 0L;
    private static Long orderId = 0L;

    public static void addProduct(Product newProduct) {
        newProduct.setId(++productId);
        products.add(newProduct);
    }

    public static void addUser(User newUser) {
        newUser.setId(++userId);
        users.add(newUser);
    }

    public static void addShoppingCart(ShoppingCart newShoppingCart) {
        newShoppingCart.setId(++shoppingCartId);
        shoppingCarts.add(newShoppingCart);
    }

    public static void addOrder(Order newOrder) {
        newOrder.setId(++orderId);
        orders.add(newOrder);
    }
}

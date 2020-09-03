package com.internet.shop.model;

import java.util.List;

public class ShoppingCart {
    private Long id;
    private List<Product> products;
    private Long userID;

    public ShoppingCart(List<Product> products, Long userID) {
        this.products = products;
        this.userID = userID;
    }
}

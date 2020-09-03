package com.internet.shop.model;

import java.util.List;

public class Order {
    private Long id;
    private List<Product> products;
    private Long userID;

    public Order(List<Product> products, Long userID) {
        this.products = products;
        this.userID = userID;
    }
}

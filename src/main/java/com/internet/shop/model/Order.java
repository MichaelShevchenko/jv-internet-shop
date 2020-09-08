package com.internet.shop.model;

import java.util.ArrayList;
import java.util.List;

public class Order implements Cloneable {
    private Long id;
    private List<Product> products;
    private Long userId;

    public Order(Long userId) {
        this.userId = userId;
        this.products = new ArrayList<>();
    }

    public Order(Long userId, List<Product> products) {
        this.userId = userId;
        this.products = copyProducts(products);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return copyProducts(products);
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", products=" + products + ", userID=" + userId + '}';
    }

    @Override
    public Order clone() {
        Order orderCopy = new Order(userId, copyProducts(products));
        orderCopy.setId(id);
        return orderCopy;
    }

    private List<Product> copyProducts(List<Product> products) {
        List<Product> orderedProductsCopy = new ArrayList<>();
        for (Product product : products) {
            orderedProductsCopy.add(product.clone());
        }
        return orderedProductsCopy;
    }
}

package com.internet.shop.model;

import java.util.ArrayList;
import java.util.List;

public class Order implements Cloneable {
    private Long id;
    private List<Product> products;
    private Long userID;

    public Order(Long userID) {
        this.userID = userID;
        this.products = new ArrayList<>();
    }

    public Order(Long userID, List<Product> products) {
        this.userID = userID;
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

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", products=" + products + ", userID=" + userID + '}';
    }

    @Override
    public Order clone() {
        Order orderCopy = new Order(userID, copyProducts(products));
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

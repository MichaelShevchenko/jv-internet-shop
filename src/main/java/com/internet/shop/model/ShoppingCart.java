package com.internet.shop.model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart implements Cloneable {
    private Long id;
    private List<Product> products;
    private Long userId;

    public ShoppingCart(Long userId) {
        this.userId = userId;
        this.products = new ArrayList<>();
    }

    public ShoppingCart(Long userId, List<Product> products) {
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
        return "ShoppingCart{" + "id=" + id + ", products=" + products
                + ", userID=" + userId + '}';
    }

    @Override
    public ShoppingCart clone() {
        ShoppingCart shoppingCartCopy = new ShoppingCart(userId, copyProducts(products));
        shoppingCartCopy.setId(id);
        return shoppingCartCopy;
    }

    private List<Product> copyProducts(List<Product> products) {
        List<Product> productsInCartCopied = new ArrayList<>();
        for (Product product : products) {
            productsInCartCopied.add(product.clone());
        }
        return productsInCartCopied;
    }
}

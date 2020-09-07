package com.internet.shop.model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart implements Cloneable {
    private Long id;
    private List<Product> products;
    private Long userID;

    public ShoppingCart(Long userID) {
        this.userID = userID;
        this.products = new ArrayList<>();
    }

    public ShoppingCart(Long userID, List<Product> products) {
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
        return "ShoppingCart{" + "id=" + id + ", products=" + products
                + ", userID=" + userID + '}';
    }

    @Override
    public ShoppingCart clone() {
        ShoppingCart shoppingCartCopy = new ShoppingCart(userID, copyProducts(products));
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

package com.internet.shop.model;

public class Product implements Cloneable {
    private Long id;
    private String name;
    private Double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name='" + name
                + '\'' + ", price=" + price + '}';
    }

    @Override
    public Product clone() {
        Product productCopy = new Product(name, price.doubleValue());
        productCopy.setId(id);
        return productCopy;
    }
}

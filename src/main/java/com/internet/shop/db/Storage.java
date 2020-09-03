package com.internet.shop.db;

import com.internet.shop.model.Product;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static Long productId = 0L;
    public static final List<Product> products = new ArrayList<>();

    public static void addProduct(Product newProduct) {
        newProduct.setId(++productId);
        products.add(newProduct);
    }

    public static boolean removeProduct(Product product) {
        return products.removeIf(p -> product.getId().equals(p.getId()));
    }
}

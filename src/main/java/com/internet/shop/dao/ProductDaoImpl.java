package com.internet.shop.dao;

import com.internet.shop.db.Storage;
import com.internet.shop.lib.Dao;
import com.internet.shop.model.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Dao
public class ProductDaoImpl implements ProductDao {

    @Override
    public Product create(Product product) {
        Storage.addProduct(product);
        return product;
    }

    @Override
    public Optional<Product> get(Long productId) {
        return Storage.products.stream()
                .filter(p -> p.getId().equals(productId))
                .findAny();
    }

    @Override
    public Product update(Product product) {
        return IntStream.range(0, Storage.products.size())
                .filter(i -> Storage.products.get(i).getId().equals(product.getId()))
                .mapToObj(i -> Storage.products.set(i, product))
                .findAny()
                .orElse(null);
    }

    @Override
    public boolean delete(Long productId) {
        Optional<Product> toDelete = get(productId);
        if (toDelete.isPresent()) {
            Storage.removeProduct(toDelete.get());
            return true;
        }
        return false;
    }

    @Override
    public List<Product> getAll() {
        List<Product> productsCopy = new ArrayList<>();
        for (Product product : Storage.products) {
            productsCopy.add(product.clone());
        }
        return productsCopy;
    }
}

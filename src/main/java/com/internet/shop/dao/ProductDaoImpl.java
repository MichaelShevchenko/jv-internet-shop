package com.internet.shop.dao;

import com.internet.shop.db.Storage;
import com.internet.shop.lib.Dao;
import com.internet.shop.model.Product;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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
                .map(Product::clone)
                .findFirst();
    }

    @Override
    public Product update(Product product) {
        IntStream.range(0, Storage.products.size())
                .filter(i -> Storage.products.get(i).getId().equals(product.getId()))
                .forEach(i -> Storage.products.set(i, product));
        return product;
    }

    @Override
    public boolean delete(Long productId) {
        return Storage.products.removeIf(p -> p.getId().equals(productId));
    }

    @Override
    public List<Product> getAll() {
        return Storage.products.stream()
                .map(Product::clone)
                .collect(Collectors.toList());
    }
}

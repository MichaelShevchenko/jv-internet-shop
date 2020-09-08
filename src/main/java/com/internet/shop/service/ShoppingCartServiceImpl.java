package com.internet.shop.service;

import com.internet.shop.dao.ShoppingCartDao;
import com.internet.shop.lib.Inject;
import com.internet.shop.lib.Service;
import com.internet.shop.model.Product;
import com.internet.shop.model.ShoppingCart;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Inject
    private ShoppingCartDao shoppingCartDao;

    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        return shoppingCartDao.create(shoppingCart);
    }

    @Override
    public ShoppingCart addProduct(ShoppingCart shoppingCart, Product product) {
        List<Product> productsInCart = shoppingCart.getProducts();
        productsInCart.add(product);
        shoppingCart.setProducts(productsInCart);
        return shoppingCartDao.update(shoppingCart);
    }

    @Override
    public boolean deleteProduct(ShoppingCart shoppingCart, Product product) {
        List<Product> productsInCart = shoppingCart.getProducts();
        boolean result = productsInCart.removeIf(p -> p.getId().equals(product.getId()));
        shoppingCart.setProducts(productsInCart);
        shoppingCartDao.update(shoppingCart);
        return result;
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        List<Product> productsInCart = shoppingCart.getProducts();
        productsInCart.clear();
        shoppingCart.setProducts(productsInCart);
        shoppingCartDao.update(shoppingCart);
    }

    @Override
    public ShoppingCart getByUserId(Long userId) {
        return shoppingCartDao.getByUserId(userId).get();
    }

    @Override
    public boolean delete(ShoppingCart shoppingCart) {
        return shoppingCartDao.delete(shoppingCart);
    }
}

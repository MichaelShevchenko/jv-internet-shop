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
        List<Product> productsInCart = shoppingCartDao
                                        .getByUserId(shoppingCart.getUserID()).get().getProducts();
        productsInCart.add(product);
        ShoppingCart updated = new ShoppingCart(shoppingCart.getUserID(), productsInCart);
        updated.setId(shoppingCart.getId());
        return shoppingCartDao.update(updated);
    }

    @Override
    public boolean deleteProduct(ShoppingCart shoppingCart, Product product) {
        List<Product> productsInCart = shoppingCartDao
                                        .getByUserId(shoppingCart.getUserID()).get().getProducts();
        if (productsInCart.size() < 1) {
            return false;
        }
        boolean result = productsInCart.removeIf(p -> p.getId().equals(product.getId()));
        ShoppingCart updated = new ShoppingCart(shoppingCart.getUserID(), productsInCart);
        updated.setId(shoppingCart.getId());
        shoppingCartDao.update(updated);
        return result;
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        List<Product> productsInCart = shoppingCartDao
                                        .getByUserId(shoppingCart.getUserID()).get().getProducts();
        productsInCart.clear();
        ShoppingCart updated = new ShoppingCart(shoppingCart.getUserID(), productsInCart);
        updated.setId(shoppingCart.getId());
        shoppingCartDao.update(updated);
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

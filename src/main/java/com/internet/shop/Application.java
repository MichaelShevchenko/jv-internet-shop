package com.internet.shop;

import com.internet.shop.dao.ProductDao;
import com.internet.shop.lib.Injector;
import com.internet.shop.model.Product;

public class Application {
    private static Injector injector = Injector.getInstance("com.internet.shop");

    public static void main(String[] args) {
        ProductDao productDaoJdbc = (ProductDao) injector.getInstance(ProductDao.class);
        System.out.println(productDaoJdbc.getAll());
        Product cpu = new Product("i9-10900K", 650);
        productDaoJdbc.create(cpu);
        System.out.println(productDaoJdbc.getAll());
    }
}

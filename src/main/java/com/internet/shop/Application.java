package com.internet.shop;

import com.internet.shop.dao.ProductDao;
import com.internet.shop.lib.Injector;

public class Application {
    private static Injector injector = Injector.getInstance("com.internet.shop");

    public static void main(String[] args) {
        ProductDao productDaoJdbc = (ProductDao) injector.getInstance(ProductDao.class);
        System.out.println(productDaoJdbc.getAll());
    }
}

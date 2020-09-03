package com.internet.shop;

import com.internet.shop.lib.Injector;
import com.internet.shop.model.Product;
import com.internet.shop.service.ProductService;
import java.util.List;

public class Application {
    private static Injector injector = Injector.getInstance("com.internet.shop");
    private static final double NEW_PRICE = 1500;
    private static final long PRESENT_PRODUCT = 4L;
    private static final long ABSENT_PRODUCT = 7L;
    private static final long TO_DELETE = 3L;

    public static void main(String[] args) {
        ProductService productService = (ProductService) injector.getInstance(ProductService.class);

        Product cpu = new Product("i9-10900K", 650);
        Product motherboard = new Product("MSI Z490 GODLIKE", 900);
        Product ram = new Product("HyperX DDR4-3600 65536MB", 400);
        Product cooler = new Product("MSI Core Frozr XL", 150);

        productService.create(cpu);
        productService.create(motherboard);
        productService.create(cooler);
        productService.delete(TO_DELETE);
        productService.create(ram);
        System.out.println(productService.getAll().toString());

        Product gpu = new Product("GeForce RTX 2080Ti", 1150);
        productService.create(gpu);
        Product gpuToUpdate = gpu.clone();
        gpuToUpdate.setPrice(NEW_PRICE);
        System.out.println(productService.update(gpuToUpdate));
        System.out.println(productService.getAll());

        Product searchedProduct = productService.get(PRESENT_PRODUCT);
        searchedProduct.setName("HyperX Fury DDR4-3600 65536MB");
        System.out.println(searchedProduct);
        System.out.println(productService.get(PRESENT_PRODUCT));
        System.out.println(productService.get(ABSENT_PRODUCT));

        List<Product> copiedStorage = productService.getAll();
        copiedStorage.add(cooler);
        System.out.println(copiedStorage);
        System.out.println(productService.getAll());
    }
}

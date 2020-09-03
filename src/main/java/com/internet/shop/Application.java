package com.internet.shop;

import com.internet.shop.lib.Injector;
import com.internet.shop.model.Product;
import com.internet.shop.service.ProductService;
import java.math.BigDecimal;

public class Application {
    private static Injector injector = Injector.getInstance("com.internet.shop");

    public static void main(String[] args) {
        ProductService productService = (ProductService) injector.getInstance(ProductService.class);

        Product cpu = new Product("i9-10900K", 650);
        Product motherboard = new Product("MSI Z490 GODLIKE", 900);
        Product ram = new Product("HyperX DDR4-3600 65536MB", 400);
        Product gpu = new Product("GeForce RTX 2080Ti", 1150);
        Product cooler = new Product("MSI Core Frozr XL", 150);

        productService.create(cpu);
        productService.create(motherboard);
        productService.create(cooler);
        productService.delete((long) 3);
        productService.create(ram);
        System.out.println(productService.getAll().toString());

        productService.create(gpu);
        Product gpuToUpdate = gpu.clone();
        gpuToUpdate.setPrice(BigDecimal.valueOf((long) 1500));
        productService.update(gpuToUpdate);
        System.out.println(productService.getAll());

        System.out.println(productService.get((long) 7));
    }
}

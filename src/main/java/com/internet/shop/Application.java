package com.internet.shop;

import com.internet.shop.db.Storage;
import com.internet.shop.lib.Injector;
import com.internet.shop.model.Product;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.model.User;
import com.internet.shop.service.OrderService;
import com.internet.shop.service.ProductService;
import com.internet.shop.service.ShoppingCartService;
import com.internet.shop.service.UserService;
import java.util.List;

public class Application {
    private static Injector injector = Injector.getInstance("com.internet.shop");
    private static final double NEW_PRICE = 1500;
    private static final String NEW_LOGIN = "Mr.Feynman";
    private static final long PRESENT_PRODUCT = 4L;
    private static final long PRESENT_USER = 2L;
    private static final long SECOND_PRESENT_USER = 3L;
    private static final long ORDER_PRESENT_USER = 4L;
    private static final long ORDER_SECOND_PRESENT_USER = 5L;
    private static final long PRESENT_ORDER = 3L;
    private static final long PRODUCT_TO_DELETE = 3L;
    private static final long USER_TO_DELETE = 1L;
    private static final long ORDER_TO_DELETE = 2L;

    public static void main(String[] args) {
        ProductService productService = (ProductService) injector.getInstance(ProductService.class);
        productCheck(productService);

        UserService userService = (UserService) injector.getInstance(UserService.class);
        userCheck(userService);

        ShoppingCartService shoppingCartService = (ShoppingCartService) injector
                                                    .getInstance(ShoppingCartService.class);
        shoppingCartCheck(shoppingCartService, productService);

        OrderService orderService = (OrderService) injector.getInstance(OrderService.class);
        orderCheck(orderService, shoppingCartService, productService);
    }

    private static void productCheck(ProductService productService) {
        Product cpu = new Product("i9-10900K", 650);
        Product motherboard = new Product("MSI Z490 GODLIKE", 900);
        Product ram = new Product("HyperX DDR4-3600 65536MB", 400);
        Product cooler = new Product("MSI Core Frozr XL", 150);

        productService.create(cpu);
        productService.create(motherboard);
        productService.create(cooler);
        productService.delete(PRODUCT_TO_DELETE);
        productService.create(ram);
        System.out.println(productService.getAll());

        Product gpu = new Product("GeForce RTX 2080Ti", 1150);
        productService.create(gpu);
        Product gpuToUpdate = gpu.clone();
        gpuToUpdate.setPrice(NEW_PRICE);
        System.out.println(productService.update(gpuToUpdate));
        System.out.println(productService.getAll());

        List<Product> copiedProductsStorage = productService.getAll();
        copiedProductsStorage.add(cooler);
        System.out.println(copiedProductsStorage);
        System.out.println(productService.getAll());

        Product searchedProduct = productService.get(PRESENT_PRODUCT);
        searchedProduct.setName("HyperX Fury DDR4-3600 65536MB");
        System.out.println(searchedProduct);
        System.out.println(productService.get(PRESENT_PRODUCT));
    }

    private static void userCheck(UserService userService) {
        User visitor = new User("Jack", "jackie", "p@ssw0rd");
        User buyer = new User("Chuck", "TexasRanger", "M@rtia1Arts");
        User explorer = new User("Richard", "Diagram", "N0be1Prize");

        userService.create(visitor);
        userService.create(buyer);
        userService.create(explorer);
        System.out.println(userService.getAll());

        User toUpdate = explorer.clone();
        toUpdate.setLogin(NEW_LOGIN);
        userService.update(toUpdate);
        System.out.println(userService.getAll());
        User searchedUser = userService.get(PRESENT_USER);
        searchedUser.setName("Walker");
        System.out.println(userService.get(PRESENT_USER));
        System.out.println(searchedUser);

        List<User> copiedUsersStorage = userService.getAll();
        userService.delete(USER_TO_DELETE);
        System.out.println(userService.getAll());
        System.out.println(copiedUsersStorage);
    }

    private static void shoppingCartCheck(ShoppingCartService shoppingCartService,
                                          ProductService productService) {
        Product cpu = new Product("i9-10900K", 650);
        Product motherboard = new Product("MSI Z490 GODLIKE", 900);
        Product ram = new Product("HyperX DDR4-3600 65536MB", 400);
        Product cooler = new Product("MSI Core Frozr XL", 150);
        Product gpu = new Product("GeForce RTX 2080Ti", 1150);
        productService.create(gpu);
        productService.create(cpu);
        productService.create(motherboard);
        productService.create(cooler);
        productService.create(ram);

        ShoppingCart buyerShoppingCart = new ShoppingCart(PRESENT_USER);
        ShoppingCart explorerShoppingCart = new ShoppingCart(SECOND_PRESENT_USER);
        shoppingCartService.create(buyerShoppingCart);
        shoppingCartService.create(explorerShoppingCart);
        System.out.println(shoppingCartService.getByUserId(PRESENT_USER));
        shoppingCartService.addProduct(shoppingCartService.getByUserId(PRESENT_USER), cooler);
        shoppingCartService.addProduct(shoppingCartService.getByUserId(PRESENT_USER), motherboard);
        shoppingCartService.addProduct(shoppingCartService.getByUserId(SECOND_PRESENT_USER), cpu);
        shoppingCartService.addProduct(shoppingCartService.getByUserId(SECOND_PRESENT_USER), gpu);
        shoppingCartService.deleteProduct(shoppingCartService
                .getByUserId(SECOND_PRESENT_USER), gpu);
        shoppingCartService.addProduct(shoppingCartService.getByUserId(SECOND_PRESENT_USER), ram);
        System.out.println(shoppingCartService.getByUserId(PRESENT_USER));
        System.out.println(shoppingCartService.getByUserId(SECOND_PRESENT_USER));
        System.out.println(Storage.shoppingCarts);

        explorerShoppingCart = shoppingCartService.getByUserId(SECOND_PRESENT_USER);
        shoppingCartService.clear(explorerShoppingCart);
        System.out.println(Storage.shoppingCarts);
        shoppingCartService.delete(explorerShoppingCart);
        System.out.println(Storage.shoppingCarts);
        ShoppingCart shoppingCartCopy = shoppingCartService.getByUserId(PRESENT_USER);
        shoppingCartCopy.setUserId(3L);
        System.out.println(shoppingCartCopy);
        System.out.println(Storage.shoppingCarts);
    }

    private static void orderCheck(OrderService orderService,
                                   ShoppingCartService shoppingCartService,
                                   ProductService productService) {
        Product cpu = new Product("i9-10900K", 650);
        Product motherboard = new Product("MSI Z490 GODLIKE", 900);
        Product ram = new Product("HyperX DDR4-3600 65536MB", 400);
        Product cooler = new Product("MSI Core Frozr XL", 150);
        Product gpu = new Product("GeForce RTX 2080Ti", 1150);
        productService.create(gpu);
        productService.create(cpu);
        productService.create(motherboard);
        productService.create(cooler);
        productService.create(ram);

        ShoppingCart buyerShoppingCart = new ShoppingCart(ORDER_PRESENT_USER);
        ShoppingCart explorerShoppingCart = new ShoppingCart(ORDER_SECOND_PRESENT_USER);
        shoppingCartService.create(buyerShoppingCart);
        shoppingCartService.create(explorerShoppingCart);
        shoppingCartService.addProduct(shoppingCartService.getByUserId(ORDER_PRESENT_USER), cooler);
        shoppingCartService.addProduct(shoppingCartService
                .getByUserId(ORDER_PRESENT_USER), motherboard);
        shoppingCartService.addProduct(shoppingCartService
                .getByUserId(ORDER_SECOND_PRESENT_USER), cpu);
        shoppingCartService.addProduct(shoppingCartService
                .getByUserId(ORDER_SECOND_PRESENT_USER), gpu);
        shoppingCartService.addProduct(shoppingCartService
                .getByUserId(ORDER_SECOND_PRESENT_USER), ram);
        System.out.println(shoppingCartService.getByUserId(ORDER_PRESENT_USER));
        System.out.println(shoppingCartService.getByUserId(ORDER_SECOND_PRESENT_USER));
        System.out.println(Storage.shoppingCarts);

        buyerShoppingCart = shoppingCartService.getByUserId(ORDER_PRESENT_USER);
        explorerShoppingCart = shoppingCartService.getByUserId(ORDER_SECOND_PRESENT_USER);
        orderService.completeOrder(buyerShoppingCart);
        orderService.completeOrder(explorerShoppingCart);
        System.out.println(orderService.getAll());
        orderService.delete(ORDER_TO_DELETE);
        System.out.println(orderService.getAll());
        System.out.println(Storage.shoppingCarts);

        ShoppingCart secondUserAnotherPurchase = new ShoppingCart(ORDER_PRESENT_USER);
        shoppingCartService.create(secondUserAnotherPurchase);
        shoppingCartService.addProduct(shoppingCartService.getByUserId(ORDER_PRESENT_USER), ram);
        shoppingCartService.addProduct(shoppingCartService.getByUserId(ORDER_PRESENT_USER), cpu);
        secondUserAnotherPurchase = shoppingCartService.getByUserId(ORDER_PRESENT_USER);
        orderService.completeOrder(secondUserAnotherPurchase);
        System.out.println(orderService.getUserOrders(ORDER_PRESENT_USER));
        System.out.println(orderService.get(PRESENT_ORDER));
    }
}

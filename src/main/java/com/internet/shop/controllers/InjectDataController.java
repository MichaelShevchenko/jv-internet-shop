package com.internet.shop.controllers;

import com.internet.shop.lib.Injector;
import com.internet.shop.model.Product;
import com.internet.shop.model.Role;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.model.User;
import com.internet.shop.service.ProductService;
import com.internet.shop.service.ShoppingCartService;
import com.internet.shop.service.UserService;
import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InjectDataController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private final UserService userService = (UserService) injector.getInstance(UserService.class);
    private final ProductService productService = (ProductService) injector
            .getInstance(ProductService.class);
    private final ShoppingCartService shoppingCartService = (ShoppingCartService) injector
            .getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User admin = new User("Admin", "admin", "matestudent");
        admin.setRoles(Set.of(Role.of("ADMIN")));
        userService.create(admin);
        User adminAdvanced = new User("Admin", "SuperUser", "matestudent");
        admin.setRoles(Set.of(Role.of("ADMIN"), Role.of("USER")));
        userService.create(adminAdvanced);
        User visitor = new User("Jack", "jackie", "p@ssw0rd");
        visitor.setRoles(Set.of(Role.of("USER")));
        userService.create(visitor);
        shoppingCartService.create(new ShoppingCart(visitor.getId()));
        User buyer = new User("Chuck", "TexasRanger", "M@rtia1Arts");
        buyer.setRoles(Set.of(Role.of("USER")));
        userService.create(buyer);
        shoppingCartService.create(new ShoppingCart(buyer.getId()));
        User explorer = new User("Richard", "Diagram", "N0be1Prize");
        explorer.setRoles(Set.of(Role.of("USER")));
        userService.create(explorer);
        shoppingCartService.create(new ShoppingCart(explorer.getId()));

        Product cpu = new Product("i9-10900K", 650);
        productService.create(cpu);
        Product motherboard = new Product("MSI Z490 GODLIKE", 900);
        productService.create(motherboard);
        Product ram = new Product("HyperX DDR4-3600 65536MB", 400);
        productService.create(ram);
        Product cooler = new Product("MSI Core Frozr XL", 150);
        productService.create(cooler);
        Product gpu = new Product("GeForce RTX 2080Ti", 1150);
        productService.create(gpu);

        req.getRequestDispatcher("/WEB-INF/views/injectData.jsp").forward(req, resp);
    }
}

package com.internet.shop.controllers.order;

import com.internet.shop.lib.Injector;
import com.internet.shop.service.OrderService;
import com.internet.shop.service.ShoppingCartService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfirmOrderController extends HttpServlet {
    private static final Long USER_ID = 1L;
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private final OrderService orderService = (OrderService) injector
            .getInstance(OrderService.class);
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (shoppingCartService.getByUserId(USER_ID).getProducts().size() == 0) {
            req.setAttribute("message", "There are no products in your shopping cart."
                    + "Please, choose a product you would like to purchase to be able to proceed");
            req.getRequestDispatcher("/WEB-INF/views/shopping-carts/products.jsp")
                    .forward(req, resp);
        } else {
            orderService.completeOrder(shoppingCartService.getByUserId(USER_ID));
            resp.sendRedirect(req.getContextPath() + "/orders/history");
        }
    }
}

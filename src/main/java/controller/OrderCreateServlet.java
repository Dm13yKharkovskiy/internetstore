package controller;

import factory.CartServiceFactory;
import factory.OrderServiceFactory;
import model.Order;
import model.Product;
import model.User;
import service.CartService;
import service.OrderService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;


public class OrderCreateServlet extends HttpServlet {
    private static final OrderService ORDER_SERVICE = OrderServiceFactory.getOrderService();
    private static final CartService CART_SERVICE = CartServiceFactory.getCartService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            ORDER_SERVICE.createOrder(user.getId());
            long cartId = CART_SERVICE.getCartId(user.getId());
            CART_SERVICE.clearCart(cartId);
            response.sendRedirect("/orders");
        }else {
            response.sendRedirect("/sing-in");
        }

    }
}

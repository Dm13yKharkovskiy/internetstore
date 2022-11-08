package controller;

import factory.OrderServiceFactory;
import model.Order;
import model.Product;
import model.User;
import service.OrderService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;


public class OrdersAllServlet extends HttpServlet {
    private final OrderService ORDER_SERVICE = OrderServiceFactory.getOrderService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            List<Order> orders = ORDER_SERVICE.getOrders(user.getId());
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("/orders.jsp").forward(request, response);
        }else {
            response.sendRedirect("/");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

package controller;

import dao.OrderDao;
import factory.OrderDaoFactory;
import factory.OrderServiceFactory;
import model.Code;
import model.User;
import service.OrderService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;


public class OrderConfirmServlet extends HttpServlet {
    private final OrderService ORDER_SERVICE = OrderServiceFactory.getOrderService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long idOrder = Long.parseLong(request.getParameter("id_order"));
        int confirmCode = Integer.parseInt(request.getParameter("confirm_code"));
        Code code = new Code(idOrder, confirmCode);
        if (ORDER_SERVICE.getConfirmCode(code)) {
            ORDER_SERVICE.changeStatusOrder("Подтвержден", idOrder);
        }

        response.sendRedirect("/orders");

    }
}

package controller;

import factory.OrderServiceFactory;
import model.Code;
import model.User;
import service.MessageService;
import service.OrderService;
import service.impl.MessageServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;


public class CodeSendToEmailServlet extends HttpServlet {
    private final MessageService MESSAGE_SERVICE = new MessageServiceImpl();
    private final OrderService ORDER_SERVICE = OrderServiceFactory.getOrderService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long idOrder = Long.parseLong(request.getParameter("idOrder"));
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            Code code = new Code(idOrder);
            MESSAGE_SERVICE.sendMessage(code, user.getEmail());
            ORDER_SERVICE.setConfirmCode(code);
            response.sendRedirect("/orders");
        } else {
            response.sendRedirect("/");
        }

    }
}

package controller;

import factory.CartServiceFactory;
import model.User;
import service.CartService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


public class CartRemoveProductServlet extends HttpServlet {
    private static final CartService CART_SERVICE = CartServiceFactory.getCartService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            long idProduct = Long.parseLong(request.getParameter("id"));
            long idUser = user.getId();
            CART_SERVICE.removeProduct(idUser, idProduct);
        }
        response.sendRedirect("/cart");
    }

}

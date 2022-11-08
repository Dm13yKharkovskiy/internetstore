package controller;

import dao.CartDao;
import dao.ProductDao;
import factory.CartDaoFactory;
import factory.CartServiceFactory;
import factory.ProductDaoFactory;
import factory.ProductServiceFactory;
import model.User;
import service.CartService;
import service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class ProductAddToCartServlet extends HttpServlet {
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
            CART_SERVICE.addProduct(idUser, idProduct);
            request.setAttribute("message", "Товар добавлен в корзину");
        }
        response.sendRedirect("/catalog");
    }
}

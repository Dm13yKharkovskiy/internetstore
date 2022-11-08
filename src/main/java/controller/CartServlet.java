package controller;

import dao.ProductDao;
import factory.CartServiceFactory;
import factory.ProductDaoFactory;
import factory.ProductServiceFactory;
import model.Product;
import model.User;
import service.CartService;
import service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;


public class CartServlet extends HttpServlet {
    private static final CartService CART_SERVICE = CartServiceFactory.getCartService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            List<Product> cart = CART_SERVICE.getProducts(user.getId());
            request.setAttribute("cart", cart);
            double amountCart = CART_SERVICE.getAmountCart(cart);
            long idCart = CART_SERVICE.getCartId(user.getId());
            request.setAttribute("amountCart", amountCart);
            request.setAttribute("idCart", idCart);
            request.getRequestDispatcher("/cart.jsp").forward(request, response);
        }else {
            response.sendRedirect("/sing-in");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

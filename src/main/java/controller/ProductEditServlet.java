package controller;


import dao.ProductDao;
import factory.ProductDaoFactory;
import factory.ProductServiceFactory;
import model.Product;
import service.ProductService;
import service.impl.ProductServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Optional;


public class ProductEditServlet extends HttpServlet {

    private static final ProductService PRODUCT_SERVICE = ProductServiceFactory.getProductServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long idProduct = Long.parseLong(request.getParameter("id"));
        Optional product = PRODUCT_SERVICE.getProductForId(idProduct);
        if (product.isPresent()) {
            Product productEdit = (Product) product.get();
            request.setAttribute("product", productEdit);
            request.getRequestDispatcher("/edit-product.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        long idProduct = Long.parseLong(request.getParameter("idProduct"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");
        PRODUCT_SERVICE.updateProduct(new Product(idProduct, name, price, description));
        response.sendRedirect("/catalog");
    }
}

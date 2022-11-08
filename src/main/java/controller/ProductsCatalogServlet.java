package controller;

import dao.ProductDao;
import factory.ProductDaoFactory;
import model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;


public class ProductsCatalogServlet extends HttpServlet {
    private static final ProductDao PRODUCT_DAO = ProductDaoFactory.getProductDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> catalog = PRODUCT_DAO.getCatalog();
        request.setAttribute("catalog", catalog);
        request.getRequestDispatcher("/catalog.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

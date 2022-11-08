package controller;

import dao.ProductDao;
import factory.ProductDaoFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class ProductRemoveServlet extends HttpServlet {
    private static final ProductDao PRODUCT_SERVICE_IMPL = ProductDaoFactory.getProductDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if(id != null) {
            long idLong = Long.parseLong(id);
            PRODUCT_SERVICE_IMPL.removeProduct(idLong);
        }
        response.sendRedirect("/catalog");
    }
}

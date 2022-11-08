package controller;

import dao.UserDao;
import factory.UserDaoFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class UserRemoveServlet extends HttpServlet {
    private static final UserDao userDao = UserDaoFactory.getUserDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        userDao.removeUser(request.getParameter("id"));
        response.sendRedirect("/admin/users");
    }
}

package controller;

import factory.UserServiceFactory;
import model.User;
import service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class UsersGetAllServlet extends HttpServlet {
    private static final UserService USER_SERVICE = UserServiceFactory.getUserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = USER_SERVICE.getAllUser();
        request.setAttribute("allUsers", users);
        request.getRequestDispatcher("/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}


package controller;

import dao.UserDao;
import factory.UserDaoFactory;
import factory.UserServiceFactory;
import model.User;
import org.apache.log4j.Logger;
import service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Optional;


public class UserSingInServlet extends HttpServlet {
    private static final UserService USER_SERVICE = UserServiceFactory.getUserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/sing.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Optional<User> user = USER_SERVICE.findUserByEmailPassword(email, password);
        if(user.isPresent()) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user.get());
            session.setAttribute("role", "admin");
            response.addCookie(new Cookie("adminTE", "TE"));
            response.sendRedirect("/");
        }else {
            request.setAttribute("error", "Неверный логин или пароль!!");
            request.getRequestDispatcher("/sing.jsp").forward(request, response);
        }
    }
}

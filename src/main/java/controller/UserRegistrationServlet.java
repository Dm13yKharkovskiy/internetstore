package controller;

import dao.UserDao;
import factory.CartServiceFactory;
import factory.UserDaoFactory;
import factory.UserServiceFactory;
import model.User;
import service.CartService;
import service.UserService;
import validation.StringValidation;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Optional;

public class UserRegistrationServlet extends HttpServlet {
    private static final UserService USER_SERVICE = UserServiceFactory.getUserService();
    private static final CartService CART_SERVICE = CartServiceFactory.getCartService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeatPassword");
        HttpSession session = request.getSession();
        if(session.getAttribute("isRegister") != null){
            response.getWriter().println("Пользователь уже зарегестрирован true!!");
            request.getRequestDispatcher("/sing.jsp").forward(request, response);
        }
        if (!StringValidation.inputStringIsNotNull(email)
                || !StringValidation.inputStringIsNotNull(password)
                || !StringValidation.inputStringIsNotNull(repeatPassword)) {
            request.setAttribute("error", "Не все поля заполнены!!!");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }

        if (!StringValidation.checkRepeatPassword(password, repeatPassword)) {
            request.setAttribute("error", "Пароли не совпадают!!!");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }

        if (USER_SERVICE.getUserByEmail(email).isPresent()) {
            request.setAttribute("error", "Данный пользователь уже существует!!!");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        } else {
            USER_SERVICE.addUser(email, password, repeatPassword);
            User userFind = USER_SERVICE.findUserByEmailPassword(email, password).get();
            CART_SERVICE.createCart(userFind.getId());
            session.setAttribute("isRegister", true);
            response.sendRedirect("/");
        }
    }
}

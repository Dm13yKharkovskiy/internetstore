package filters;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/admin/*"})
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        User userFromSession = (User) httpRequest.getSession().getAttribute("user");
        if (userFromSession != null && userFromSession.getRole().trim().equals("admin")) {
            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect("403.jsp");
        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

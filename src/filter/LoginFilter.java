package filter;

import Service.UserService;
import model.User;

import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@WebFilter("/*")
public class LoginFilter implements javax.servlet.Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        if (httpServletRequest.getUserPrincipal() != null && httpServletRequest.getSession().getAttribute("user") == null) {
            try {
                saveInSession(httpServletRequest);
            } catch (NamingException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    private void saveInSession(HttpServletRequest req) throws NamingException, NoSuchAlgorithmException {
        UserService userService = new UserService();
        String username = req.getUserPrincipal().getName();
        User byUsername = userService.getByUsername(username);
        req .getSession(true).setAttribute("user",byUsername);
    }
    @Override
    public void destroy() {

    }
}

package controller;

import Service.UserService;
import model.User;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@WebServlet("/profile")
public class ProfileController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getUserPrincipal() != null) {
            req.getRequestDispatcher("WEB-INF/profile.jsp").forward(req, resp);
        } else {
            resp.sendError(403);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Implement showing all of User finds / show number of UP AND DOWN VOTES on User finds
        if (req.getUserPrincipal() != null) {
            User user = (User) req.getSession().getAttribute("user");
            user.setPassword(req.getParameter("inputPassword"));
            UserService userService = new UserService();
            try {
                userService.updateUser(user);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NamingException e) {
                e.printStackTrace();
            }

        } else {
            resp.sendError(403);
        }
        resp.sendRedirect(req.getContextPath()+ "/");
    }
}

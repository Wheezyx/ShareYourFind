package controller;


import Service.UserService;
import org.springframework.dao.DuplicateKeyException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@WebServlet("/register")
public class RegisterController extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String email = req.getParameter("inputEmail");
        String pass = req.getParameter("inputPassword");
        String username = req.getParameter("inputUsername");
        UserService service = new UserService();
        try {
            service.addUser(username,email,pass);
            resp.sendRedirect(req.getContextPath()+ "/");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (DuplicateKeyException e)
        {
            req.setAttribute("existingUser", "Username/email already taken.");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        }
    }
}

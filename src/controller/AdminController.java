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

@WebServlet("/admin")
public class AdminController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/admin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        manageUser(req, resp);
        req.getRequestDispatcher("WEB-INF/admin.jsp").forward(req, resp);
    }

    private void manageUser(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("inputUsername");
        if (!username.isEmpty()) {
            UserService userService = new UserService();
            try {
                User user = userService.getByUsername(username);
                if (req.getParameter("option").equals("ban")) user.setActive(false);
                if (req.getParameter("option").equals("unban")) user.setActive(true);
                userService.updateUser(user);
                req.setAttribute("banOperation", "User banned");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NamingException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                req.setAttribute("banOperation", "Cannot find the user");
            }
        } else {
            req.setAttribute("banOperation", "You input empty string, try again!");
        }
    }

    private void manageFind(HttpServletRequest req, HttpServletResponse resp) {
        //GET FIND BY NAME, TAKE THE ID
        //DELETE EVERY VOTE WITH FIND ID
        //DELETE FIND by ID
        //set correct Attribute and remake admin.jsp
    }

}


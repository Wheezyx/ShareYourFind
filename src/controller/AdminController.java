package controller;

import Service.FindService;
import Service.UserService;
import model.Find;
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
        manageFind(req, resp);
        req.getRequestDispatcher("WEB-INF/admin.jsp").forward(req, resp);
    }

    private void manageUser(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("inputUsername");
        if (username != null && !username.isEmpty()) {
            UserService userService = new UserService();
            try {
                User user = userService.getByUsername(username);
                if (req.getParameter("option").equals("ban")) user.setActive(false);
                if (req.getParameter("option").equals("unban")) user.setActive(true);
                userService.updateUser(user);
                req.setAttribute("banOperation", "User banned");
            } catch (NoSuchAlgorithmException | NamingException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                req.setAttribute("banOperation", "Cannot find the user");
            }
        } else {
            req.setAttribute("banOperation", "You input empty username, try again!");
        }
    }

    private void manageFind(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("inputFindName");
        if (name != null && !name.isEmpty()) {
            FindService findService = new FindService();
            try {
                Find find = findService.getByName(name);
                if (find.getDownVote() > 0 || find.getDownVote() > 0) {
                    //delete all votes by with ID
                }
                findService.deleteFind(find.getId());
                req.setAttribute("deleteFindOperation", "Find removed with all votes");
            } catch (NullPointerException e) {
                req.setAttribute("deleteFindOperation", "Find not found!");
            } catch (NoSuchAlgorithmException | NamingException e) {
                e.printStackTrace();
            }
        } else {
            req.setAttribute("deleteFindOperation", "You input empty name of Found, try again!");
        }
    }

}


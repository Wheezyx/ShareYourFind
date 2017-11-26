package controller;

import Service.FindService;
import model.User;
import org.springframework.dao.DuplicateKeyException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@WebServlet("/add")
public class AddFindController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getUserPrincipal() != null) {
            req.getRequestDispatcher("new.jsp").forward(req, resp);
        } else {
            resp.sendError(403);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("inputName");
        String description = req.getParameter("inputDescription");
        String url = req.getParameter("inputUrl");
        User authUser = (User) req.getSession().getAttribute("user");
        if (req.getUserPrincipal() != null) {
            FindService findService = new FindService();
            try {
                findService.addFind(name, description, url, authUser);
                resp.sendRedirect(req.getContextPath() + "/");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NamingException e) {
                e.printStackTrace();
            } catch (DuplicateKeyException e) {
                req.setAttribute("existingFind", "Find already exists");
                req.getRequestDispatcher("new.jsp").forward(req, resp);
            }
        } else
            resp.sendError(403);
    }
}

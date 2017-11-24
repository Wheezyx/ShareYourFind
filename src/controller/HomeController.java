package controller;

import Service.FindService;
import model.Find;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Comparator;
import java.util.List;

@WebServlet("")
public class HomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            saveFindsRequest(req);
            req.getRequestDispatcher("index.jsp").forward(req,resp);
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private void saveFindsRequest(HttpServletRequest req) throws NamingException, NoSuchAlgorithmException {
        FindService findService = new FindService();
        List<Find> allFinds = findService.getAllFinds(new Comparator<Find>() {
            @Override
            public int compare(Find o1, Find o2) {
                int vote1 = o1.getUpVote() - o1.getDownVote();
                int vote2 = o2.getUpVote() - o2.getDownVote();
                if (vote1 < vote2)
                    return 1;
                else if (vote1 > vote2)
                    return -1;
                return 0;
            }
        });
        req.setAttribute("finds",allFinds);
    }
}

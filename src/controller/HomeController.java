package controller;

import Service.FindService;
import model.Find;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@WebServlet("")
public class HomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        System.out.println(action);
        int FindsPerPage = 4;
        List<Find> list = null;
        HttpSession session = req.getSession();
        if (action == null || action.equals("load")) {
            session = req.getSession();
            session.setAttribute("pageNumber", 1);
            try {
                list = getList(0, FindsPerPage);
            } catch (NamingException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            session.setAttribute("list", list);
            session.setAttribute("startIndex", 0);
            session.setAttribute("btnNext", "Next");
            session.setAttribute("btnPrevious", "Previous Disabled");
            if (list != null && list.size() < FindsPerPage
                    || list.size() == 0) {
                session.setAttribute("btnNext", "");
                session.setAttribute("btnPrevious", "");
            }
        }
        req.getRequestDispatcher("WEB-INF/index.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println(action);
        int FindsPerPage = 4;
        List<Find> list = null;
        HttpSession session = request.getSession();
        // next-is for next page to display
        if (action != null && action.equals("next")) {
            session.setAttribute("btnPrevious", "Previous");
            session.setAttribute("startIndex",
                    (Integer) session.getAttribute("startIndex")
                            + FindsPerPage);
            try {
                list = getList((Integer) session.getAttribute("startIndex"),
                        FindsPerPage);
            } catch (NamingException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            // Calculating and setting page number
            int startIndex = (Integer) session.getAttribute("startIndex");
            int pageNumber = startIndex / FindsPerPage;
            session.setAttribute("pageNumber", ++pageNumber);
            // Set list in session to display in page
            session.setAttribute("list", list);
            int size1 = 0;
            try {
                size1 = getList(
                        (Integer) session.getAttribute("startIndex")
                                + FindsPerPage,
                        FindsPerPage).size();
            } catch (NamingException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            // logic to disable Next button.
            if (list != null && list.size() < FindsPerPage || size1 == 0) {
                session.setAttribute("btnNext", "Next Disabled");
                session.setAttribute("btnPrevious", "Previous");
            }
        } else if (action != null && action.equals("previous")) {
            session.setAttribute("btnNext", "Next");
            session.setAttribute("startIndex",
                    (Integer) session.getAttribute("startIndex")
                            - FindsPerPage);
            try {
                list = getList((Integer) session.getAttribute("startIndex"),
                        FindsPerPage);
            } catch (NamingException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            // Calculating and setting page number
            int startIndex = (Integer) session.getAttribute("startIndex");
            int pageNumber = startIndex / FindsPerPage;
            session.setAttribute("pageNumber", ++pageNumber);
            // Set list in session to display in page
            session.setAttribute("list", list);
            // logic to disable Previous button.
            if ((Integer) session.getAttribute("startIndex") <= 0) {
                session.setAttribute("btnPrevious", "Previous Disabled");
                session.setAttribute("btnNext", "Next");
                session.setAttribute("startIndex", 0);
            } else {
                session.setAttribute("btnNext", "Next");
            }
        }
        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);

        /*FindService findService = new FindService();
        List<Find> allFinds = findService.getAllFinds((o1, o2) -> {
            int vote1 = o1.getUpVote() - o1.getDownVote();
            int vote2 = o2.getUpVote() - o2.getDownVote();
            if (vote1 < vote2)
                return 1;
            else if (vote1 > vote2)
                return -1;
            return 0;
        });
        /*List<Find> allFindByDate =
                findService.getAllFinds((d1, d2) -> d2.getTimestamp().compareTo(d1.getTimestamp()));
        req.setAttribute("finds", allFinds);
        */
    }

    private List<Find> getList(int startIndex, int numberOfArticlePerpage) throws NamingException, NoSuchAlgorithmException {
        FindService findService = new FindService();
        return findService.getSpecifiedAmount(startIndex, numberOfArticlePerpage);
    }
}

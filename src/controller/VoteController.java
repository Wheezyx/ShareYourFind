package controller;

import Service.FindService;
import Service.VoteService;
import model.Find;
import model.User;
import model.Vote;
import model.VoteType;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@WebServlet("/vote")
public class VoteController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User logged = (User) req.getSession().getAttribute("user");
        if (logged != null) {
            VoteType voteType = VoteType.valueOf(req.getParameter("vote"));
            System.out.println(req.getParameter("vote"));
            long userId = logged.getId();
            long findId = Long.parseLong(req.getParameter("find_id"));
            try {
                updateVote(userId, findId, voteType);
            } catch (NamingException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        resp.sendRedirect(req.getContextPath() + "/");
    }

    private void updateVote(long userId, long findId, VoteType voteType) throws NamingException, NoSuchAlgorithmException {
        VoteService voteService = new VoteService();
        Vote currentVote = voteService.getVoteByFindUserId(findId, userId);
        Vote updatedVote = voteService.addOrUpdateVote(findId, userId, voteType);
        if (currentVote != updatedVote || !updatedVote.equals(currentVote)) {
            updateFind(findId, currentVote, updatedVote);
        }
    }

    private void updateFind(long findId, Vote oldVote, Vote newVote) throws NamingException, NoSuchAlgorithmException {
        FindService findService = new FindService();
        Find findById = findService.getFindById(findId);
        Find updated = null;
        if (oldVote == null && newVote != null) {
            updated = addFindVote(findById, newVote.getVoteType());
        } else if (oldVote != null && newVote != null) {
            updated = removeDiscoveryVote(findById, oldVote.getVoteType());
            updated = addFindVote(updated, newVote.getVoteType());
        }
        findService.updateFind(updated);
    }

    private Find addFindVote(Find find, VoteType voteType) {
        Find copiedFind = new Find(find);
        if (voteType == VoteType.VOTE_UP) {
            copiedFind.setUpVote(copiedFind.getUpVote() + 1);
        } else if (voteType == VoteType.VOTE_DOWN) {
            copiedFind.setDownVote(copiedFind.getDownVote() + 1);
        }
        return copiedFind;
    }

    private Find removeDiscoveryVote(Find find, VoteType voteType) {
        Find copiedFind = new Find(find);
        if (voteType == VoteType.VOTE_UP) {
            copiedFind.setUpVote(copiedFind.getUpVote() - 1);
        } else if (voteType == VoteType.VOTE_DOWN) {
            copiedFind.setDownVote(copiedFind.getDownVote() - 1);
        }
        return copiedFind;
    }
}

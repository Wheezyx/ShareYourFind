package Service;

import dao.DAOFactory;
import dao.VoteDAO;
import model.Vote;
import model.VoteType;

import javax.naming.NamingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;

public class VoteService {
    public Vote addVote(long findId, long userId, VoteType voteType) throws NoSuchAlgorithmException, NamingException {
        Vote vote = new Vote();
        vote.setFindId(findId);
        vote.setUserId(userId);
        vote.setDate(new Timestamp(new Date().getTime()));
        vote.setVoteType(voteType);
        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        VoteDAO dao = daoFactory.getVoteDAO();
        vote = dao.create(vote);
        return vote;
    }

    public Vote updateVote(long findId, long userId, VoteType voteType) throws NoSuchAlgorithmException, NamingException {
        DAOFactory daofactory = DAOFactory.getDAOFactory();
        VoteDAO dao = daofactory.getVoteDAO();
        Vote update = dao.getVoteByUserIdFindID(userId, findId);
        if (update != null) {
            update.setVoteType(voteType);
            dao.update(update);
        }
        return update;
    }

    public Vote addOrUpdateVote(long findId, long userId, VoteType voteType) throws NoSuchAlgorithmException, NamingException {
        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        VoteDAO dao = daoFactory.getVoteDAO();
        Vote vote = dao.getVoteByUserIdFindID(userId, findId);
        Vote resultVote = null;
        if (vote == null) {
            resultVote = addVote(findId, userId, voteType);
        } else {
            resultVote = updateVote(findId, userId, voteType);
        }
        return resultVote;
    }

    public Vote getVoteByFindUserId(long findId, long userId) throws NoSuchAlgorithmException, NamingException {
        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        VoteDAO dao = daoFactory.getVoteDAO();
        Vote vote = dao.getVoteByUserIdFindID(userId, findId);
        return vote;
    }
}


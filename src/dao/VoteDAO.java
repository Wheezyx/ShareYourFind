package dao;

import model.Vote;

public interface VoteDAO extends GenericDAO<Vote,Long> {

    Vote getVoteByUserIdFindID(long userId, long findId);
}

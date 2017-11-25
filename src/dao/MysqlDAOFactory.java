package dao;

import javax.naming.NamingException;

public class MysqlDAOFactory extends DAOFactory {

    @Override
    public UserDAO getUserDAO() throws NamingException {
        return new UserImpl();
    }
    @Override
    public FindDAO getFindDAO() throws NamingException {
        return new FindImpl();
    }

    @Override
    public VoteDAO getVoteDAO() throws NamingException {
        return new VoteImpl();
    }


}

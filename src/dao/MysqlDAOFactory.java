package dao;

import javax.naming.NamingException;

public class MysqlDAOFactory extends DAOFactory {

    @Override
    public UserDAO gerUserDAO() throws NamingException {
        return new UserImpl();
    }
}

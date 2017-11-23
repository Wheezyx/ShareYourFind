package dao;

import javax.naming.NamingException;

public class MysqlDAOFactory extends DAOFactory {

    @Override
    public UserDAO getUserDAO() throws NamingException {
        return new UserImpl();
    }
    public FindDAO getFindDAO()
    {
        return new FindImpl();
    }
}

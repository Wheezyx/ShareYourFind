package dao;

import javax.naming.NamingException;
import java.security.NoSuchAlgorithmException;

public abstract class DAOFactory {

    private static final int MYSQL_DAO_FACTORY = 0;
    public abstract UserDAO getUserDAO() throws NamingException;

    public static DAOFactory getDAOFactory() throws NoSuchAlgorithmException {

        DAOFactory factory;
        factory = getDAOFactory(MYSQL_DAO_FACTORY);
        return factory;
    }
    private static DAOFactory getDAOFactory(int type) throws NoSuchAlgorithmException {
        switch (type)
        {
            case MYSQL_DAO_FACTORY:
                return new MysqlDAOFactory();
        default:
            throw new NoSuchAlgorithmException();
        }

    }
}

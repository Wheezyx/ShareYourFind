package dao;

public class MysqlDAOFactory extends DAOFactory {

    @Override
    public UserDAO gerUserDAO() {
        return new UserImpl();
    }
}

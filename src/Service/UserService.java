package Service;

import dao.DAOFactory;
import dao.UserDAO;
import model.User;

import javax.naming.NamingException;
import java.security.NoSuchAlgorithmException;

public class UserService {
    public void addUser(String username, String email, String password) throws NoSuchAlgorithmException, NamingException {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setActive(true);
        DAOFactory factory = DAOFactory.getDAOFactory();
        UserDAO userDAO = factory.getUserDAO();
        userDAO.create(user);
    }

    public User getById(long userId) throws NoSuchAlgorithmException, NamingException {
        DAOFactory factory = DAOFactory.getDAOFactory();
        UserDAO userDAO = factory.getUserDAO();
        return userDAO.read(userId);
    }

    public User getByUsername(String username) throws NoSuchAlgorithmException, NamingException {
        DAOFactory factory = DAOFactory.getDAOFactory();
        UserDAO userDAO = factory.getUserDAO();
        return userDAO.getByUsername(username);
    }
    public boolean updateUser(User user) throws NoSuchAlgorithmException, NamingException {
        DAOFactory factory = DAOFactory.getDAOFactory();
        UserDAO dao = factory.getUserDAO();
        boolean result = dao.update(user);
        return result;
    }
}

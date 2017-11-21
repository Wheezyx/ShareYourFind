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
        UserDAO userDAO = factory.gerUserDAO();
        userDAO.create(user);
    }

}

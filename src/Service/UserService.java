package Service;

import dao.DAOFactory;
import dao.UserDAO;
import model.User;

import javax.naming.NamingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserService {
    public void addUser(String username, String email, String password) throws NoSuchAlgorithmException, NamingException {
        User user = new User();
        user.setUsername(username);
        //String md5Password = encryptPassword(password);
        user.setPassword(password);
        user.setEmail(email);
        user.setActive(true);
        DAOFactory factory = DAOFactory.getDAOFactory();
        UserDAO userDAO = factory.getUserDAO();
        userDAO.create(user);
    }

  /*
  private String encryptPassword(String password) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        digest.update(password.getBytes());
        String md5Password = new BigInteger(1, digest.digest()).toString(16);
        return md5Password;
    }
*/
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
        return dao.update(user);
    }
}

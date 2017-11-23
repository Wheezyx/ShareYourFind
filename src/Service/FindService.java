package Service;

import dao.DAOFactory;
import dao.FindDAO;
import model.Find;
import model.User;

import javax.naming.NamingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;

public class FindService {

    public void addFind(String name, String desc, String url, User user) throws NoSuchAlgorithmException, NamingException {
        Find find = new Find();
        find.setName(name);
        find.setDescritpion(desc);
        find.setUrl(url);
        User clonedUser = new User(user);
        find.setUser(clonedUser);
        find.setTimestamp(new Timestamp(new Date().getTime()));

        DAOFactory factory = DAOFactory.getDAOFactory();
        FindDAO dao = factory.getFindDAO();
        dao.create(find);
    }
}

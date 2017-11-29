package Service;

import dao.DAOFactory;
import dao.FindDAO;
import model.Find;
import model.User;

import javax.naming.NamingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

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

    public List<Find> getAllFinds(Comparator<Find> comparator) throws NoSuchAlgorithmException, NamingException {
        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        FindDAO dao = daoFactory.getFindDAO();
        List<Find> finds = dao.getAll();
        if (comparator != null && finds != null) {
            finds.sort(comparator);
        }
        return finds;
    }

    public Find getFindById(long findId) throws NoSuchAlgorithmException, NamingException {
        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        FindDAO dao = daoFactory.getFindDAO();
        Find find = dao.read(findId);
        return find;
    }

    public boolean updateFind(Find find) throws NoSuchAlgorithmException, NamingException {
        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        FindDAO dao = daoFactory.getFindDAO();
        boolean result = dao.update(find);
        return result;
    }

    public List<Find> getSpecifiedAmount(int startIndex, int amount) throws NoSuchAlgorithmException, NamingException {
        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        FindDAO dao = daoFactory.getFindDAO();
        List<Find> finds = dao.getSpecifiedAmount(startIndex,amount);
        return finds;
    }
}

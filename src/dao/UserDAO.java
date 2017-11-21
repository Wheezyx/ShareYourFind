package dao;

import model.User;

public interface UserDAO extends GenericDAO<User, Long> {

    User getByUsername(String username);
}

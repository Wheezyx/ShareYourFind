package dao;

import model.User;

public class UserImpl implements UserDAO {

    @Override
    public User getUserByUsername(String username) {
        return null;
    }

    @Override
    public User create(User newObj) {
        return null;
    }

    @Override
    public User read(Long pKey) {
        return null;
    }

    @Override
    public boolean update(User updateObj) {
        return false;
    }

    @Override
    public boolean delete(Long key) {
        return false;
    }
}

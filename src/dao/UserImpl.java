package dao;

import model.User;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import util.ConnectionProvider;

import javax.naming.NamingException;

public class UserImpl implements UserDAO {

    private static final String CREATE = "INSERT INTO user(username,email, is_active, password) VALUES (:username, :email, :active, :password);";

    private NamedParameterJdbcTemplate jdbcTemplate;

    public UserImpl() throws NamingException {
        jdbcTemplate = new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
    }
    @Override
    public User create(User newObj) {
        User createdUser = new User(newObj);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(newObj);
        int update = jdbcTemplate.update(CREATE, parameterSource, keyHolder);
        if (update != 0) {
            createdUser.setId((Long) keyHolder.getKey());
            setRole(createdUser);
        }
        return createdUser;
    }

    private void setRole(User user)
    {
        final String userRole = "INSERT INTO user_role(username) VALUES(:username)";
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(user);
        jdbcTemplate.update(userRole, parameterSource);
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

    @Override
    public User getUserByUsername(String username) {
        return null;
    }
}

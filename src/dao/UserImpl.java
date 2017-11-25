package dao;

import model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import util.ConnectionProvider;

import javax.naming.NamingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserImpl implements UserDAO {

    private static final String CREATE = "INSERT INTO user(username,email, is_active, password) VALUES (:username, :email, :active, :password);";
    private static final String READ =
            "SELECT user_id, username, email, password, is_active FROM user WHERE user_id = :id";
    private static final String READ_BY_USERNAME =
            "SELECT user_id, username, email, password, is_active FROM user WHERE username = :username";
    private static final String UPDATE = "UPDATE user SET password=:password WHERE user_id=:user_id;";

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

    private void setRole(User user) {
        final String userRole = "INSERT INTO user_role(username) VALUES(:username)";
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(user);
        jdbcTemplate.update(userRole, parameterSource);
    }

    @Override
    public User read(Long pKey) {
        User readedUser;
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", pKey);
        readedUser = jdbcTemplate.queryForObject(READ, parameterSource, new RowMapp());
        return readedUser;
    }

    @Override
    public boolean update(User updateObj) {
        boolean result = false;
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("password",updateObj.getPassword());
        paramMap.put("user_id",updateObj.getId());
        SqlParameterSource parameterSource = new MapSqlParameterSource(paramMap);
        int update = jdbcTemplate.update(UPDATE,parameterSource);
        if (update > 0)
        {
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(Long key) {
        return false;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getByUsername(String username) {
        User readedUser;
        SqlParameterSource parameterSource = new MapSqlParameterSource("username", username);
        readedUser = jdbcTemplate.queryForObject(READ_BY_USERNAME, parameterSource, new RowMapp());
        return readedUser;
    }

    private class RowMapp implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User();
            user.setId(resultSet.getLong("user_id"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            user.setUsername(resultSet.getString("username"));
            return user;
        }
    }
}

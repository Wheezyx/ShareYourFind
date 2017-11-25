package dao;

import model.Find;
import model.User;
import org.springframework.jdbc.core.RowMapper;
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

public class FindImpl implements FindDAO {

    private static final String CREATE = "INSERT INTO find(name, description, url, user_id, date, up_vote, down_vote) "
            + "VALUES(:name, :description, :url, :user_id, :date, :up_vote, :down_vote);";

    private static final String READ_ALL =
            "SELECT user.user_id, username, email, is_active, password, find_id, name, description, url, date, up_vote, down_vote "
                    + "FROM find LEFT JOIN user ON find.user_id=user.user_id;";
    private static final String UPDATE =
            "UPDATE find SET name=:name, description=:description, url=:url, user_id=:user_id, date=:date, up_vote=:up_vote, down_vote=:down_vote "
                    + "WHERE find_id=:find_id;";
    private static final String READ =
            "SELECT user.user_id, username, email, is_active, password, find_id, name, description, url, date, up_vote, down_vote "
                    + "FROM find LEFT JOIN user ON find.user_id=user.user_id WHERE find_id=:find_id;";
    private NamedParameterJdbcTemplate jdbcTemplate;

    public FindImpl() throws NamingException {
        jdbcTemplate = new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
    }

    @Override
    public Find create(Find newObj) {
        Find result = new Find(newObj);
        KeyHolder holder = new GeneratedKeyHolder();
        Map<String, Object> map = new HashMap<>();
        map.put("name", result.getName());
        map.put("description", result.getDescritpion());
        map.put("url", result.getUrl());
        map.put("user_id", result.getUser().getId());
        map.put("date", result.getTimestamp());
        map.put("up_vote", result.getUpVote());
        map.put("down_vote", result.getDownVote());
        SqlParameterSource parameterSource = new MapSqlParameterSource(map);
        int update = jdbcTemplate.update(CREATE, parameterSource, holder);
        if (update > 0)
            result.setId((Long) holder.getKey());
        return result;
    }

    @Override
    public Find read(Long pKey) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("find_id",pKey);
        Find find = jdbcTemplate.queryForObject(READ,parameterSource, new FindRowMapper());
        return find;
    }

    @Override
    public boolean update(Find updateObj) {
        boolean result = false;
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("find_id", updateObj.getId());
        paramMap.put("name", updateObj.getName());
        paramMap.put("description", updateObj.getDescritpion());
        paramMap.put("url", updateObj.getUrl());
        paramMap.put("user_id", updateObj.getUser().getId());
        paramMap.put("date", updateObj.getTimestamp());
        paramMap.put("up_vote", updateObj.getUpVote());
        paramMap.put("down_vote", updateObj.getDownVote());
        SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
        int update = jdbcTemplate.update(UPDATE, paramSource);
        if(update > 0) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(Long key) {
        return false;
    }

    @Override
    public List<Find> getAll() {
        List<Find> finds = jdbcTemplate.query(READ_ALL,new FindRowMapper());
        return finds;
    }

    public class FindRowMapper implements RowMapper<Find>
    {

        @Override
        public Find mapRow(ResultSet resultSet, int i) throws SQLException {
            Find find = new Find();
            find.setId(resultSet.getLong("find_id"));
            find.setName(resultSet.getString("name"));
            find.setDescritpion(resultSet.getString("description"));
            find.setUrl(resultSet.getString("url"));
            find.setTimestamp(resultSet.getTimestamp("date"));
            find.setUpVote(resultSet.getInt("up_vote"));
            find.setDownVote(resultSet.getInt("down_vote"));
            User user = new User();
            user.setId(resultSet.getLong("user_id"));
            user.setUsername(resultSet.getString("username"));
            user.setActive(resultSet.getBoolean("is_active"));
            user.setPassword(resultSet.getString("password"));
            find.setUser(user);
            return find;
        }
    }
}

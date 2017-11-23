package dao;

import model.Find;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindImpl implements FindDAO {

    private static final String CREATE = "INSERT INTO find(name, description,url,user_id,date,up_vote,down_vote)"
            + "VALUES(:name,:description,:url,:user_id,:up_vote,:down_vote";

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Find create(Find newObj) {
        Find result = new Find(newObj);
        KeyHolder holder = new GeneratedKeyHolder();
        Map<String,Object> map = new HashMap<>();
        map.put("name",result.getName());
        map.put("description",result.getDescritpion());
        map.put("url",result.getUrl());
        map.put("user_id",result.getUser());
        map.put("date", result.getTimestamp());
        map.put("up_vote",result.getUpVote());
        map.put("down_vote",result.getDownVote());
        SqlParameterSource parameterSource = new MapSqlParameterSource(map);
        int update = jdbcTemplate.update(CREATE,parameterSource,holder);
        if (update >= 1)
            result.setId((Long) holder.getKey());
        return result;
    }

    @Override
    public Find read(Long pKey) {
        return null;
    }

    @Override
    public boolean update(Find updateObj) {
        return false;
    }

    @Override
    public boolean delete(Long key) {
        return false;
    }

    @Override
    public List<Find> getAll() {
        return null;
    }
}

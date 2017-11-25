package dao;

import model.Vote;
import model.VoteType;
import org.springframework.dao.EmptyResultDataAccessException;
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

public class VoteImpl implements VoteDAO {

    private NamedParameterJdbcTemplate jdbcTemplate;

    private static final String CREATE = "INSERT INTO vote(find_id, user_id, date, type) "
            + "VALUES (:find_id, :user_id, :date, :type);";
    private static final String READ = "SELECT vote_id, find_id, user_id, date, type "
            + "FROM vote WHERE vote_id = :vote_id;";
    private static final String READ_BY_FIND_USE_IDS = "SELECT vote_id, find_id, user_id, date, type "
            + "FROM vote WHERE user_id = :user_id AND find_id = :find_id;";
    private static final String UPDATE= "UPDATE vote SET date=:date, type=:type WHERE vote_id=:vote_id;";


    public VoteImpl() throws NamingException {
        jdbcTemplate = new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
    }


    @Override
    public Vote getVoteByUserIdFindID(long userId, long findId) {
        Map<String,Object> map = new HashMap<>();
        map.put("user_id",userId);
        map.put("find_id",findId);
        SqlParameterSource parameterSource = new MapSqlParameterSource(map);
        Vote vote = null;
        try {
            vote = jdbcTemplate.queryForObject(READ_BY_FIND_USE_IDS,parameterSource, new VoteRowMapper());
        } catch (EmptyResultDataAccessException e )
        {
            System.out.println("Not found"); // just to check
        }
        return vote;
    }

    @Override
    public Vote create(Vote newObj) {
        Vote clonedVote = new Vote(newObj);
        Map<String,Object> map = new HashMap<>();
        map.put("find_id",clonedVote.getFindId());
        map.put("user_id",clonedVote.getUserId());
        map.put("date", clonedVote.getDate());
        map.put("type", clonedVote.getVoteType().toString());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource parameterSource = new MapSqlParameterSource(map);
        int update = jdbcTemplate.update(CREATE,parameterSource,keyHolder);
        if (update > 0)
        {
            clonedVote.setId((Long) keyHolder.getKey());
        }
        return clonedVote;
    }

    @Override
    public Vote read(Long pKey) {
        SqlParameterSource paramSource = new MapSqlParameterSource("vote_id", pKey);
        Vote vote = jdbcTemplate.queryForObject(READ, paramSource, new VoteRowMapper());
        return vote;
    }

    @Override
    public boolean update(Vote updateObj) {
        boolean result = false;
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("date", updateObj.getDate());
        paramMap.put("type", updateObj.getVoteType().toString());
        paramMap.put("vote_id", updateObj.getId());
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
    public List<Vote> getAll() {
        return null;
    }

    private class VoteRowMapper implements RowMapper<Vote> {
        @Override
        public Vote mapRow(ResultSet resultSet, int row) throws SQLException {
            Vote vote = new Vote();
            vote.setId(resultSet.getLong("vote_id"));
            vote.setUserId(resultSet.getLong("user_id"));
            vote.setFindId(resultSet.getLong("find_id"));
            vote.setDate(resultSet.getTimestamp("date"));
            vote.setVoteType(VoteType.valueOf(resultSet.getString("type")));
            return vote;
        }

    }
}

package model;

import java.sql.Timestamp;

public class Vote {
    private long id;
    private long findId;
    private long userId;
    private Timestamp date;
    private VoteType voteType;

    public Vote(Vote vote) {
        this.findId = vote.findId;
        this.userId = vote.userId;
        this.date = vote.date;
        this.voteType = vote.voteType;
    }
public Vote(){}
    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", findId=" + findId +
                ", userId=" + userId +
                ", timestamp=" + date +
                ", voteType=" + voteType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vote vote = (Vote) o;

        if (id != vote.id) return false;
        if (findId != vote.findId) return false;
        if (userId != vote.userId) return false;
        if (date != null ? !date.equals(vote.date) : vote.date != null) return false;
        return voteType == vote.voteType;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (findId ^ (findId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (voteType != null ? voteType.hashCode() : 0);
        return result;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFindId() {
        return findId;
    }

    public void setFindId(long findId) {
        this.findId = findId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp timestamp) {
        this.date = timestamp;
    }

    public VoteType getVoteType() {
        return voteType;
    }

    public void setVoteType(VoteType voteType) {
        this.voteType = voteType;
    }

    public Vote(long id, long findId, long userId, Timestamp timestamp, VoteType voteType) {
        this.id = id;
        this.findId = findId;
        this.userId = userId;
        this.date = timestamp;
        this.voteType = voteType;
    }
}

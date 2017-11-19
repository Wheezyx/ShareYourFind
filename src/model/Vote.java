package model;

import java.sql.Timestamp;

public class Vote {
    private long id;
    private long discoveryID;
    private long userID;
    private Timestamp timestamp;
    private VoteType voteType;

    public Vote() {
    }

    public Vote(long id, long discoveryID, long userID, Timestamp timestamp, VoteType voteType) {
        this.id = id;
        this.discoveryID = discoveryID;
        this.userID = userID;
        this.timestamp = timestamp;
        this.voteType = voteType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDiscoveryID() {
        return discoveryID;
    }

    public void setDiscoveryID(long discoveryID) {
        this.discoveryID = discoveryID;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public VoteType getVoteType() {
        return voteType;
    }

    public void setVoteType(VoteType voteType) {
        this.voteType = voteType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vote vote = (Vote) o;

        if (id != vote.id) return false;
        if (discoveryID != vote.discoveryID) return false;
        if (userID != vote.userID) return false;
        if (timestamp != null ? !timestamp.equals(vote.timestamp) : vote.timestamp != null) return false;
        return voteType == vote.voteType;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (discoveryID ^ (discoveryID >>> 32));
        result = 31 * result + (int) (userID ^ (userID >>> 32));
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + (voteType != null ? voteType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", discoveryID=" + discoveryID +
                ", userID=" + userID +
                ", timestamp=" + timestamp +
                ", voteType=" + voteType +
                '}';
    }
}

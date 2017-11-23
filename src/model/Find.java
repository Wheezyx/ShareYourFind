package model;

import java.sql.Timestamp;

public class Find {
    private long id;
    private String name;
    private String descritpion;
    private String url;
    private Timestamp timestamp;
    private User user;
    private int upVote;
    private int downVote;


    public Find(Find newObj){
        this.id = newObj.id;
        this.name = newObj.name;
        this.descritpion = newObj.descritpion;
        this.url = newObj.url;
        this.timestamp = newObj.timestamp;
        this.user = newObj.user;
        this.upVote = newObj.upVote;
        this.downVote = newObj.downVote;
    }

    public Find(){
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescritpion() {
        return descritpion;
    }

    public void setDescritpion(String descritpion) {
        this.descritpion = descritpion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getUpVote() {
        return upVote;
    }

    public void setUpVote(int upVote) {
        this.upVote = upVote;
    }

    public int getDownVote() {
        return downVote;
    }

    public void setDownVote(int downVote) {
        this.downVote = downVote;
    }

    @Override
    public String toString() {
        return "Find{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", descritpion='" + descritpion + '\'' +
                ", url='" + url + '\'' +
                ", timestamp=" + timestamp +
                ", user=" + user +
                ", upVote=" + upVote +
                ", downVote=" + downVote +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Find find = (Find) o;

        if (id != find.id) return false;
        if (upVote != find.upVote) return false;
        if (downVote != find.downVote) return false;
        if (name != null ? !name.equals(find.name) : find.name != null) return false;
        if (descritpion != null ? !descritpion.equals(find.descritpion) : find.descritpion != null) return false;
        if (url != null ? !url.equals(find.url) : find.url != null) return false;
        if (timestamp != null ? !timestamp.equals(find.timestamp) : find.timestamp != null) return false;
        return user != null ? user.equals(find.user) : find.user == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (descritpion != null ? descritpion.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + upVote;
        result = 31 * result + downVote;
        return result;
    }
}

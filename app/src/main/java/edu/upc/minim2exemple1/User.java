package edu.upc.minim2exemple1;

public class User {
    private String username;
    private int repos;
    private int following;

    public User(){

    }
    public User(String username, int repos, int following){
        setFollowing(following);
        setRepos(repos);
        setUsername(username);
    }

    public int getFollowing() {
        return following;
    }

    public int getRepos() {
        return repos;
    }

    public String getUsername() {
        return username;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public void setRepos(int repos) {
        this.repos = repos;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

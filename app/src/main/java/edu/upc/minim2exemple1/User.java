package edu.upc.minim2exemple1;

public class User {
    private String username;
    private int repos;
    private int following;
    private int followers;
    private String avatarUrl;

    public User(){

    }
    public User(String username, int repos, int following, int followers, String avatarUrl){
        setFollowing(following);
        setRepos(repos);
        setUsername(username);
        setFollowers(followers);
        setAvatarUrl(avatarUrl);
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

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}

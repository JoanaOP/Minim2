package edu.upc.minim2exemple1;

public class Follower {

    private String avatarUrl;
    private String name;

    public Follower(){

    }
    public Follower(String name, String avatarUrl){
        setName(name);
        setAvatarUrl(avatarUrl);
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getName() {
        return name;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setName(String name) {
        this.name = name;
    }
}

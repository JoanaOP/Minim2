package edu.upc.minim2exemple1;

public class Repository {

    private String name;
    private String language;

    public Repository(){

    }
    public Repository(String name, String language){
        setLanguage(language);
        setName(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}

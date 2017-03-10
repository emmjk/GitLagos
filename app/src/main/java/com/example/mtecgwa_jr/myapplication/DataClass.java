package com.example.mtecgwa_jr.myapplication;

/**
 * Created by mtecgwa-jr on 3/5/17.
 */

public class DataClass {

    private String userName , profileUrl ;
    private  int id , repositories;

    public DataClass(String userName , String profileUrl , int id , int repositories)
    {
        this.userName = userName;
        this.profileUrl = profileUrl;
        this.id = id;
        this.repositories = repositories;
    }
    public DataClass()
    {}

    public void setUserName(String userName) {this.userName = userName ;}
    public void setProfileUrl(String profileUrl) {this.profileUrl = profileUrl; }
    public void setRepositories(int repositories) {this.repositories = repositories; }
    public void setId(int id) { this.id = id; }
    public String getUserName()
    {
        return userName;
    }
    public String getProfileUrl() { return profileUrl; }
    public int getId()
    {
        return id;
    }
    public int getRepositories() { return repositories; }
}

package com.example.instagramclone.Model;

public class User1 {

    private String id;
    private String username;
    private String imageurl;
    private String status;
    private String search;

    public User1(String id, String username, String imageurl, String status, String search) {
        this.id = id;
        this.username = username;
        this.imageurl = imageurl;
        this.status = status;
        this.search = search;
    }

    public User1() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}

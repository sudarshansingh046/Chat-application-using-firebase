package com.example.instagramclone.SendNotificationPack;

public class Data {
    private String Title;
    private String Message;
    private String user;
    private int icon;
    private String body;
    private String title;
    private String sented;

    public Data(String title, String message) {
        Title = title;
        Message = message;
    }
    public Data(String user, int icon, String Message, String title, String sented) {
        this.user = user;
        this.icon = icon;
        this.Message= Message;
        this.title = title;
        this.sented = sented;
    }


    public Data() {
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String gettitle() {
        return title;
    }

    public void settitle(String title) {
        this.title = title;
    }

    public String getSented() {
        return sented;
    }

    public void setSented(String sented) {
        this.sented = sented;
    }

}

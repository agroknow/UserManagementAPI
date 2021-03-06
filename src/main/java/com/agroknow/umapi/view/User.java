package com.agroknow.umapi.view;

public class User {
    private String username;
    private String password;
    private String email;
    private int userid;

    public User() {
    }

    public User(String username, String password, String email, int userid) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.userid = userid;
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}


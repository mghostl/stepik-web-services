package com.mghostl.education.stepik.webservices.auth.dao;

public class User {
    private final String login;
    private String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(String login) {
        this(login, null);
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

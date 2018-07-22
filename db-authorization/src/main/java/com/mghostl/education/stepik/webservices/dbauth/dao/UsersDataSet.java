package com.mghostl.education.stepik.webservices.dbauth.dao;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class UsersDataSet implements Serializable {


   @Column(name = "login", unique = true, updatable = false)
    private String login;
   @Column(name = "password")
    private String password;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @SuppressWarnings("UnusedDeclaration")
    public UsersDataSet() {}

    @SuppressWarnings("UnusedDeclaration")
    public UsersDataSet(String login, String password, long id) {}

    UsersDataSet(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @SuppressWarnings("UnusedDeclaration")
    public String getLogin() {
        return login;
    }

    @SuppressWarnings("UnusedDeclaration")
    public String getPassword() {
        return password;
    }

    @SuppressWarnings("UnusedDeclaration")
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UsersDataSet{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }

    long getId() {
        return id;
    }

}

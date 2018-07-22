package com.mghostl.education.stepik.webservices.dbauth.services;

import com.mghostl.education.stepik.webservices.dbauth.dao.UsersDataSet;

public interface DBService {

    void printConnectInfo();

    long addUser(String login, String password);

    UsersDataSet getUser(Long userId);
}

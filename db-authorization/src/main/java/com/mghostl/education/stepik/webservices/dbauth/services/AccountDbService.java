package com.mghostl.education.stepik.webservices.dbauth.services;

import com.mghostl.education.stepik.webservices.auth.AccountService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AccountDbService implements AccountService {
    private final DBService dbService;
    private Map<String, Long> users = new ConcurrentHashMap<>();

    public AccountDbService(DBService dbService) {
        this.dbService = dbService;
    }

    @Override
    public boolean isNotAuth(String login) {
        return users.get(login) == null;
    }

    @Override
    public void saveUser(String login, String password) {
        Long id = dbService.addUser(login, password);
        users.put(login, id);
    }

    @Override
    public boolean checkPassword(String login, String password) {
        Long id = users.get(login);
        if (id == null) {
            return false;
        }
        return dbService.getUser(users.get(login)).getPassword().equals(password);
    }
}

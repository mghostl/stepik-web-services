package com.mghostl.education.stepik.webservices.auth;

import com.mghostl.education.stepik.webservices.auth.dao.User;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class AccountServiceSimple implements AccountService{
    private Map<String, User> users = new ConcurrentHashMap<>();

    @Override
    public boolean isNotAuth(String login) {
        return !Optional.ofNullable(users.get(login))
                .isPresent();
    }

    @Override
    public void saveUser(String login, String password) {
        users.put(login, new User(login, password));
    }

    @Override
    public boolean checkPassword(String login, String password) {
        return !isNotAuth(login) && users.get(login).getPassword().equals(password);
    }

}

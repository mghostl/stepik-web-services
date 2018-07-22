package com.mghostl.education.stepik.webservices.auth;

public interface AccountService {
    boolean isNotAuth(String login);

    void saveUser(String login, String password);

    boolean checkPassword(String login, String password);
}

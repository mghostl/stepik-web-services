package com.mghostl.education.stepik.webservices.auth.servlets;

import com.mghostl.education.stepik.webservices.auth.AccountService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static com.mghostl.education.stepik.webservices.utils.ServletUtils.getParamsMap;

public class SignUpServlet extends HttpServlet {
    private final AccountService accountService;

    public SignUpServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, String> params = getParamsMap(req);
        String login = params.get("login");
        String password = params.get("password");
        if(accountService.isNotAuth(login)) {
            accountService.saveUser(login, password);
        }
    }
}

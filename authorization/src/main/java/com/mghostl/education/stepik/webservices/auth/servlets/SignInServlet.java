package com.mghostl.education.stepik.webservices.auth.servlets;

import com.mghostl.education.stepik.webservices.auth.AccountService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

import static com.mghostl.education.stepik.webservices.utils.ServletUtils.getParamsMap;

public class SignInServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(SignInServlet.class.getName());
    private final AccountService accountService;

    public SignInServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, String> params = getParamsMap(req);
        String login = params.get("login");
        String password = params.get("password");
        if(!accountService.isNotAuth(login) && accountService.checkPassword(login, password)) {
           onAuth(login, resp);
        }
        else {
           onUnAuth(resp);
        }
    }

    private void onAuth(String login, HttpServletResponse resp) {
        resp.setStatus(HttpServletResponse.SC_OK);
        print("Authorized: " + login, resp);
    }

    private void onUnAuth(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        print("Unauthorized", response);
    }

    private void print(String message, HttpServletResponse response) {
        try {
            response.getWriter().print(message);
        } catch (IOException e) {
            LOG.info("ERROR: " + e.getMessage());
        }
    }
}

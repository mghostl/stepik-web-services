package com.mghostl.education.stepik.webservices.auth;

import com.mghostl.education.stepik.webservices.utils.ServerFactory;
import com.mghostl.education.stepik.webservices.auth.servlets.SignInServlet;
import com.mghostl.education.stepik.webservices.auth.servlets.SignUpServlet;
import org.eclipse.jetty.server.Server;

import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getGlobal();

    public static void main(String[] args) throws Exception {
        AccountService accountService = new AccountServiceSimple();

        ServerFactory.addServlet(new SignInServlet(accountService), "/signin");
        ServerFactory.addServlet(new SignUpServlet(accountService), "/signup");
        Server server = ServerFactory.createServer();
        LOGGER.info("Server started");
        server.join();

    }
}

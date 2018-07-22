package com.mghostl.education.stepik.webservices.dbauth;

import com.mghostl.education.stepik.webservices.auth.AccountService;
import com.mghostl.education.stepik.webservices.auth.servlets.SignInServlet;
import com.mghostl.education.stepik.webservices.auth.servlets.SignUpServlet;
import com.mghostl.education.stepik.webservices.dbauth.services.AccountDbService;
import com.mghostl.education.stepik.webservices.dbauth.services.DBService;
import com.mghostl.education.stepik.webservices.dbauth.services.DBServiceImpl;
import org.eclipse.jetty.server.Server;

import java.util.logging.Logger;

import static com.mghostl.education.stepik.webservices.utils.ServerFactory.addServlet;
import static com.mghostl.education.stepik.webservices.utils.ServerFactory.createServer;

public class Main {
    private static final Logger LOGGER = Logger.getGlobal();
    public static void main(String[] args) {
        DBService dbService = initDBService();
        AccountService accountService = new AccountDbService(dbService);
        addServlet(new SignInServlet(accountService), "/signin");
        addServlet(new SignUpServlet(accountService), "/signup");
        try {
            Server server = createServer();
            LOGGER.info("Server started");
            server.join();
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
        }
    }

    private static DBService initDBService() {
        DBService dbService = new DBServiceImpl();
        dbService.printConnectInfo();
        return dbService;
    }
}

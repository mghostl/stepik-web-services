package com.mghostl.education.stepik.webservices;

import com.mghostl.education.stepik.webservices.servlets.MirrorRequestsServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.http.HttpServlet;
import java.util.logging.Logger;

public class Main {
    private static ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
    private static final Logger LOGGER = Logger.getGlobal();
    public static void main(String[] args) {
        try {
            addServlet(new MirrorRequestsServlet(), "/mirror");
            Server server = createServer();
            LOGGER.info("Server started");
            server.join();
        } catch (Exception e) {
           LOGGER.info(e.getMessage());
        }
    }

    private static Server createServer() throws Exception {
        Server server = new Server(8080);
        server.setHandler(contextHandler);
        server.start();
        return server;
    }

    private static void addServlet(HttpServlet servlet, String path) {
        contextHandler.addServlet(new ServletHolder(servlet), path);
    }

}

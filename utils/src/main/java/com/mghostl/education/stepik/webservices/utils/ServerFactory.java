package com.mghostl.education.stepik.webservices.utils;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.http.HttpServlet;

public class ServerFactory {
    private static final ServletContextHandler servletContextHandler  = new ServletContextHandler(ServletContextHandler.SESSIONS);
    private static final ResourceHandler resourceHandler = new ResourceHandler();

    private ServerFactory() {}

    public static Server createServer() throws Exception {
        Server server = new Server(8080);
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{servletContextHandler, resourceHandler});
        server.setHandler(handlers);
        server.start();
        return server;
    }

    public static void addServlet(HttpServlet servlet, String path) {
        servletContextHandler.addServlet(new ServletHolder(servlet), path);
    }

    public static void addResourceBase(String resourceBase) {
        resourceHandler.setDirectoriesListed(true);
        resourceHandler.setResourceBase(resourceBase);
    }
}

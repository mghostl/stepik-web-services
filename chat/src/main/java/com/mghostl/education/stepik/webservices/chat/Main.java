package com.mghostl.education.stepik.webservices.chat;

import com.mghostl.education.stepik.webservices.chat.servlets.WebSocketChatServlet;
import com.mghostl.education.stepik.webservices.utils.ServerFactory;
import org.eclipse.jetty.server.Server;

import java.util.logging.Logger;

import static com.mghostl.education.stepik.webservices.utils.ServerFactory.addResourceBase;
import static com.mghostl.education.stepik.webservices.utils.ServerFactory.addServlet;

public class Main {
    private static final Logger LOGGER = Logger.getGlobal();
    public static void main(String[] args) {

        try {
            addResourceBase("public_html");
            addServlet(new WebSocketChatServlet(), "/chat");
            Server server = ServerFactory.createServer();
            LOGGER.info("Server started");
            server.join();
        } catch (Exception e) {
            LOGGER.info("Error: " + e.getMessage());
        }
    }
}

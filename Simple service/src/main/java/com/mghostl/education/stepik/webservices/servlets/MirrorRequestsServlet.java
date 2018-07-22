package com.mghostl.education.stepik.webservices.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class MirrorRequestsServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getGlobal();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  {
        String value = req.getParameterMap().get("key")[0];
        try {
            resp.getWriter().print(value);
        } catch (IOException e) {
           LOGGER.info("ERROR: " + e.getMessage());
        }
    }
}

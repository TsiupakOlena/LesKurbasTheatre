package com.leskurbas.controller;

import com.leskurbas.service.UserSessionService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        UserSessionService.invalidateSession(request);
        UserSessionService.deleteUserCookie(response);
        response.sendRedirect(request.getContextPath() + "/home");
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}

package com.leskurbas.controller;

import com.leskurbas.exceptions.DeletionException;
import com.leskurbas.service.PlaySessionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deletesession")
public class DeleteSession extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        long id = Long.parseLong(request.getParameter("sessionId"));

        System.out.println(id);

        PlaySessionService sessionService = new PlaySessionService();
        try {
            sessionService.deleteSession(id);
        } catch (DeletionException e) {
            e.printStackTrace();
            response.sendError(500);
        }
        response.sendRedirect(request.getContextPath() + "/adminpage");
    }
}
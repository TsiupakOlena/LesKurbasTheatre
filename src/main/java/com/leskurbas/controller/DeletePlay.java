package com.leskurbas.controller;

import com.leskurbas.exceptions.DeletionException;
import com.leskurbas.service.PlaySessionService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteplay")
public class DeletePlay extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String playName = request.getParameter("playName");
        PlaySessionService playService = new PlaySessionService();

        if (playName == null) {
            response.sendError(500);
            return;
        }

        long id = playService.getPlayIdByName(playName);
        if (id < 1) {
            response.sendError(500);
            return;
        }

        try {
            playService.deletePlay(id);
        } catch (DeletionException e) {
            e.printStackTrace();
            response.sendError(500);
        }
        response.sendRedirect(request.getContextPath() + "/adminpage");
    }
}

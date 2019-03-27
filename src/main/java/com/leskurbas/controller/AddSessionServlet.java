package com.leskurbas.controller;

import com.leskurbas.exceptions.CreationException;
import com.leskurbas.service.PlaySessionService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/addsession")
public class AddSessionServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        RequestDispatcher dispatcher;
        PlaySessionService sessionService = new PlaySessionService();
        String playName = request.getParameter("play");

        if (playName == null) {
            dispatcher = this.getServletContext()
                    .getRequestDispatcher("/WEB-INF/view/adminpage.jsp");
            dispatcher.forward(request, response);
            return;
        }

        long id = sessionService.getPlayIdByName(playName);
        if (id < 1) {
            dispatcher = this.getServletContext()
                    .getRequestDispatcher("/WEB-INF/view/adminpage.jsp");
            dispatcher.forward(request, response);
            return;
        }
        request.setAttribute("playId", id);
        dispatcher = this.getServletContext()
                .getRequestDispatcher("/WEB-INF/view/addsession.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String date = request.getParameter("date");
        String time = request.getParameter("time");
        float regular = Float.parseFloat(request.getParameter("regular"));
        float vip = Float.parseFloat(request.getParameter("vip"));
        long id = Long.parseLong(request.getParameter("playId"));

        System.out.println(id + " " + date + " " + time + " " + regular + " " + vip);

        PlaySessionService sessionService = new PlaySessionService();
        try {
            sessionService.createSession(id, date, time, regular, vip);
        } catch (CreationException e) {
            e.printStackTrace();
            response.sendError(500);
        }
        response.sendRedirect(request.getContextPath() + "/adminpage");
    }
}

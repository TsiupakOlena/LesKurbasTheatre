package com.leskurbas.controller;

import com.leskurbas.controllerutils.ControllerUtils;
import com.leskurbas.model.User;
import com.leskurbas.service.PlaySessionService;
import com.leskurbas.service.UserSessionService;
import com.leskurbas.service.UserPersonService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    private PlaySessionService playService = new PlaySessionService();
    private ControllerUtils utils = new ControllerUtils();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        request.setAttribute("plays", playService.getAllPlaysDTO());
        request = utils.checkUserNavigation(request);

        RequestDispatcher dispatcher = this.getServletContext()
                .getRequestDispatcher("/WEB-INF/view/home.jsp");
        dispatcher.forward(request, response);
    }
}
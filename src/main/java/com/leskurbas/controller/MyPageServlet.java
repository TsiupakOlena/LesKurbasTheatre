package com.leskurbas.controller;

import com.leskurbas.dto.ReservationsDTO;
import com.leskurbas.dto.SeatsDTO;
import com.leskurbas.dto.UserDTO;
import com.leskurbas.service.UserPersonService;
import com.leskurbas.service.UserSessionService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/mypage")
public class MyPageServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        UserPersonService ups = new UserPersonService();
        String userInSession = UserSessionService.getLoggedInUser(session);
        UserDTO user = ups.getUserDtoByLogin(userInSession);
        request.setAttribute("user", user);
        RequestDispatcher dispatcher = this.getServletContext().
                getRequestDispatcher("/WEB-INF/view/mycabinet.jsp");
        dispatcher.forward(request, response);
    }
}

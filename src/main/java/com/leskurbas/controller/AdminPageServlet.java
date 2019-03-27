package com.leskurbas.controller;

import com.leskurbas.dto.AdminDTO;
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

@WebServlet("/adminpage")
public class AdminPageServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        UserPersonService ups = new UserPersonService();
        String userInSession = UserSessionService.getLoggedInUser(session);
        AdminDTO admin = ups.getAdminDtoByLogin(userInSession);

        request.setAttribute("admin", admin);
        RequestDispatcher dispatcher = this.getServletContext().
                getRequestDispatcher("/WEB-INF/view/admincabinet.jsp");
        dispatcher.forward(request, response);
    }
}

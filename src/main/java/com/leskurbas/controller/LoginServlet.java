package com.leskurbas.controller;

import com.leskurbas.model.User;
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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    public static final String REMEMBER_ME = "yes";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/view/login.jsp");
        request.setAttribute("state", "ok");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException{

        UserPersonService ups = new UserPersonService();
        User user = null;
        try {
            user = ups.getUserByLoginPassword(
                    request.getParameter("login"),
                    request.getParameter("password")
            );
        } catch (Exception e) {
            System.out.println(e);
            response.sendError(505);
            return;
        }

        if(user == null) {
            request.setAttribute("state", "error");
            RequestDispatcher dispatcher = this.getServletContext().
                    getRequestDispatcher("/WEB-INF/view/login.jsp");
            dispatcher.forward(request, response);
        } else {
            HttpSession session = request.getSession();
            String login = user.getLogin();
            UserSessionService.storeLoggedInUser(session, login);

            String rememberMe = request.getParameter("rememberMe");
            if (rememberMe.equals(REMEMBER_ME)) {
                UserSessionService.storeUserCookie(response, login);
            } else {
                UserSessionService.deleteUserCookie(response);
            }

            response.sendRedirect(request.getContextPath() + "/home");
        }
    }
}
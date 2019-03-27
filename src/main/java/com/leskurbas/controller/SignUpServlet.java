package com.leskurbas.controller;

import com.leskurbas.model.Person;
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

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/view/signup.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        UserPersonService ups = new UserPersonService();
        User user = new User(
            request.getParameter("login"),
            request.getParameter("password"),
            (byte)0
        );
        Person person = new Person(
                request.getParameter("email"),
                request.getParameter("surname"),
                request.getParameter("name")
        );

        try {
            ups.createUserAndPerson(user, person);
        } catch (Exception e) {
            System.out.println(e);
            response.sendError(505);
            return;
        }
        HttpSession session = request.getSession();
        UserSessionService.storeLoggedInUser(session, user.getLogin());

        response.sendRedirect(request.getContextPath() + "/home");
    }
}
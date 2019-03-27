package com.leskurbas.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.leskurbas.service.UserPersonService;
import org.json.JSONObject;
import org.json.JSONException;


@WebServlet("/LoginValidation")
public class LoginValidation extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserPersonService ups = new UserPersonService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        JSONObject result = new JSONObject();
        try {
            if (ups.userLoginExists(login)) {
                result.put("status", "error");
                response.getWriter().append(result.toString());
            } else {
                result.put("status", "success");
                response.getWriter().append(result.toString());
            }
        } catch (Exception e) {
            System.out.println(e);
            response.sendError(505);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}


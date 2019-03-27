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


@WebServlet("/EmailValidation")
public class EmailValidation extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserPersonService ups = new UserPersonService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        JSONObject result = new JSONObject();
        try {
            if (ups.personEmailExists(email)) {
                result.put("status", "error");
                response.getWriter().append(result.toString());
                System.out.println("Error");
            } else {
                result.put("status", "success");
                response.getWriter().append(result.toString());
                System.out.println("Succsess");
            }
            System.out.println("Trying ...");
        } catch (JSONException e) {
            response.sendError(505);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
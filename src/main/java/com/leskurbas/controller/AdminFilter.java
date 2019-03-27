package com.leskurbas.controller;

import com.leskurbas.model.User;
import com.leskurbas.service.UserPersonService;
import com.leskurbas.service.UserSessionService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "adminFilter",
        urlPatterns = { "/adminpage", "/addplay",
                "/addsession", "/deleteplay",
                        "/deletesession"})
public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException,
                         ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();

        String userInSession = UserSessionService.getLoggedInUser(session);
        UserPersonService userService = new UserPersonService();
        User user = userService.getUserByLogin(userInSession);

        if (user == null || user.isAdmin() == 0) {
            HttpServletResponse httpResponse =
                    (HttpServletResponse) response;
            httpResponse.sendRedirect(httpRequest.getContextPath()
                    + "/home");
            return;
        }

        chain.doFilter(request, response);
    }
}
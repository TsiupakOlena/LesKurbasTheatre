package com.leskurbas.controller;

import com.leskurbas.service.UserPersonService;
import com.leskurbas.service.UserSessionService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "cookieFilter",
        urlPatterns = { "/*" })
public class CookieFilter implements Filter {

    public static final String COOKIE_CHECKED = "COOKIE_CHECKED";
    public static final String CHECKED = "CHECKED";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();

        String checked = (String) session.getAttribute(COOKIE_CHECKED);
        if (checked != null && checked.equals(CHECKED)) {
            chain.doFilter(request, response);
            return;
        }

        String userInSession = UserSessionService.getLoggedInUser(session);
        if (userInSession != null) {
            session.setAttribute(COOKIE_CHECKED, CHECKED);
            chain.doFilter(request, response);
            return;
        }

        String login = UserSessionService.getUserNameFromCookie(httpRequest);
        UserPersonService userPersonService = new UserPersonService();
        if (userPersonService.userLoginExists(login)) {
            UserSessionService.storeLoggedInUser(session, login);
            session.setAttribute(COOKIE_CHECKED, CHECKED);
        }

        chain.doFilter(request, response);

    }
}
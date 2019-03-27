package com.leskurbas.controller;

import com.leskurbas.service.UserSessionService;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "sessionFilter",
        urlPatterns = { "/book","/mypage" })
public class SessionFilterServlet implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();

        String checked = (String) session.getAttribute(
                CookieFilter.COOKIE_CHECKED);

        if (checked == null || !checked.equals(CookieFilter.CHECKED)) {
            HttpServletResponse httpResponse =
                    (HttpServletResponse) response;
            httpResponse.sendRedirect(httpRequest.getContextPath()
                    + "/login");
            return;
        }

        chain.doFilter(request, response);
    }
}

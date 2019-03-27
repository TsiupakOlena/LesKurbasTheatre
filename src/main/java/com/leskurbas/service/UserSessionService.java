package com.leskurbas.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserSessionService {
    private static final String USER_NAME_STORAGE = "STORE_USER_NAME_IN_COOKIE";

    public static void storeLoggedInUser(HttpSession session, String loggedInUser) {
        session.setAttribute("loggedInUser", loggedInUser);
    }

    public static String getLoggedInUser(HttpSession session) {
        String loggedInUser = (String) session.getAttribute("loggedInUser");
        return loggedInUser;
    }

    public static void invalidateSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
    }

    public static void storeUserCookie(HttpServletResponse response, String user) {
        Cookie cookieUserName = new Cookie(USER_NAME_STORAGE, user);
        cookieUserName.setMaxAge(24 * 60 * 60);
        response.addCookie(cookieUserName);
    }

    public static String getUserNameFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (USER_NAME_STORAGE.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static void deleteUserCookie(HttpServletResponse response) {
        Cookie cookieUserName = new Cookie(USER_NAME_STORAGE, null);
        cookieUserName.setMaxAge(0);
        response.addCookie(cookieUserName);
    }
}

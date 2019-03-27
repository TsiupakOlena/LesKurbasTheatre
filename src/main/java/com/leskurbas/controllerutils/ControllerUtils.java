package com.leskurbas.controllerutils;

import com.leskurbas.model.User;
import com.leskurbas.service.UserPersonService;
import com.leskurbas.service.UserSessionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ControllerUtils {

    public HttpServletRequest checkUserNavigation(HttpServletRequest request) {
        HttpSession userSession = request.getSession();
        String userLogin = UserSessionService.getLoggedInUser(userSession);
            if (userLogin != null) {
            request.setAttribute("sessionUrl", "/leskurbas/logout");
            request.setAttribute("sessionState", "Log out");

            UserPersonService userService = new UserPersonService();
            User user = userService.getUserByLogin(userLogin);
            if(user != null) {
                if (user.isAdmin() == 1) {
                    request.setAttribute("isAdmin", true);
                } else {
                    request.setAttribute("isAdmin", false);
                }
            } else {
                request.setAttribute("sessionUrl", "/leskurbas/login");
                request.setAttribute("sessionState", "Log in");
                request.setAttribute("isAdmin", false);
            }
        } else {
                request.setAttribute("sessionUrl", "/leskurbas/login");
                request.setAttribute("sessionState", "Log in");
                request.setAttribute("isAdmin", false);
        }
        return request;
    }
}

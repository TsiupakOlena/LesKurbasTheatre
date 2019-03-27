package com.leskurbas.controller;

import com.leskurbas.controllerutils.ControllerUtils;
import com.leskurbas.dto.PersonDTO;
import com.leskurbas.exceptions.UpdateException;
import com.leskurbas.model.Person;
import com.leskurbas.model.User;
import com.leskurbas.service.PlaySessionService;
import com.leskurbas.service.UserPersonService;
import com.leskurbas.service.UserSessionService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/editprofile")
@MultipartConfig
public class EditProfileServlet extends HttpServlet {

    private static final String imagesDirectory = "c:\\Users\\User\\"
            + "eclipse-workspace\\EployeeServlet\\src\\main\\webapp\\"
            + "WEB-INF\\classes\\images\\";
    private ControllerUtils utils = new ControllerUtils();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        HttpSession session = request.getSession();
        RequestDispatcher dispatcher;
        UserPersonService userService = new UserPersonService();

        String userInSession = UserSessionService.getLoggedInUser(session);
        long id = userService.getUserIdByLogin(userInSession);
        PersonDTO personDTO = userService.getPersonDtoById(id);

        request.setAttribute("person", personDTO);
        request = utils.checkUserNavigation(request);

        dispatcher = this.getServletContext()
                .getRequestDispatcher("/WEB-INF/view/editprofile.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        long id = Integer.parseInt(
                new String(readPart(request, "id"))
        );

        UserPersonService ups = new UserPersonService();

        User user = new User(
                new String(readPart(request, "login")),
                new String(readPart(request, "password"))
        );

        user.setId(id);
        if (request.getPart("image") != null) {
            String fileName = "profilePic" + user.getLogin() + id;
            user.setPicture(fileName);

            byte[] fileBuffer = readPart(request, "image");
            File targetFile = new File(imagesDirectory + fileName);
            System.out.println(targetFile.getAbsolutePath());
            OutputStream outStream = new FileOutputStream(targetFile);
            outStream.write(fileBuffer);
            outStream.close();
        }

        Person person = new Person(
                new String(readPart(request, "email")),
                new String(readPart(request, "surname")),
                new String(readPart(request, "name"))
        );
        try {
            ups.updateUserPerson(user, person);
        } catch (UpdateException e) {
            e.printStackTrace();
            response.sendError(505);
            return;
        }
        response.sendRedirect(request.getContextPath()
                + "/editprofile");
    }

    private byte[] readPart(HttpServletRequest request, String param)
            throws IOException, ServletException {

        Part part = request.getPart(param);
        InputStream content = part.getInputStream();
        byte[] buffer = new byte[content.available()];
        content.read(buffer);

        return buffer;
    }
}
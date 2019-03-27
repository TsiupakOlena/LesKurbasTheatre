package com.leskurbas.controller;

import com.leskurbas.exceptions.CreationException;
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
import java.nio.file.Paths;

@WebServlet("/addplay")
@MultipartConfig
public class AddPlayServlet extends HttpServlet {

    private static final String imagesDirectory = "c:\\Users\\User\\"
            + "eclipse-workspace\\EployeeServlet\\src\\main\\webapp\\"
            + "WEB-INF\\classes\\images\\";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        RequestDispatcher dispatcher = this.getServletContext()
                .getRequestDispatcher("/WEB-INF/view/addplay.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String name = new String(readPart(request, "name"));
        String time = new String(readPart(request, "time"));

        String fileName = name.replaceAll(" ","-");
        System.out.println(fileName);
        byte[] fileBuffer = readPart(request, "image");

        File targetFile = new File(imagesDirectory + fileName);
        System.out.println(targetFile.getAbsolutePath());
        OutputStream outStream = new FileOutputStream(targetFile);
        outStream.write(fileBuffer);
        outStream.close();

        PlaySessionService playService = new PlaySessionService();
        try {
            playService.createPlay(name, time, fileName);
        } catch (CreationException e) {
            e.printStackTrace();
            response.sendError(500);
            return;
        }
        response.sendRedirect(request.getContextPath() + "/adminpage");
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

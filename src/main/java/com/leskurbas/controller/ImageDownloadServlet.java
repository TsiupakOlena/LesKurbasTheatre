package com.leskurbas.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/image")
public class ImageDownloadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String imagesDirectory = "c:\\Users\\User\\"
            + "eclipse-workspace\\EployeeServlet\\src\\main\\webapp\\"
            + "WEB-INF\\classes\\images\\";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       try {
            String fileName = request.getParameter("picture");
            File targetFile = new File(imagesDirectory + fileName);
            byte[] fileContent = Files.readAllBytes(targetFile.toPath());
            response.getOutputStream().write(fileContent);
            response.setContentType("image/png");
            response.setContentLength(fileContent.length);
            response.getOutputStream().write(fileContent);
       } catch (Exception e) {
           System.out.println(e);
       }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}

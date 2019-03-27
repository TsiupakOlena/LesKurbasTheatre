package com.leskurbas.controller;

import com.leskurbas.dto.BookingSessionDTO;
import com.leskurbas.exceptions.ReservationException;
import com.leskurbas.model.Seat;

import com.leskurbas.service.PlaySessionService;
import com.leskurbas.service.ReservationService;
import com.leskurbas.service.UserPersonService;
import com.leskurbas.service.UserSessionService;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

@WebServlet("/book")
public class BookingServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String session = request.getParameter("session");
        PlaySessionService service = new PlaySessionService();

        long sessionId = Integer.parseInt(session);
        BookingSessionDTO bookingDto = service.getBookingBySessionId(sessionId);

        if (bookingDto != null) {
            request.setAttribute("session", bookingDto);
            RequestDispatcher dispatcher = this.getServletContext().
                    getRequestDispatcher("/WEB-INF/view/book.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendError(404);
        }
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                          throws IOException {

        JSONArray placesToBook;
        String chosenPlaces;
        long sessionId;
        int size;

        chosenPlaces = request.getParameter("bookedPlaces");
        sessionId = Integer.parseInt(request.getParameter("sessionID"));
        placesToBook = new JSONArray(chosenPlaces);
        size = placesToBook.length();

        List<Seat> seats = new ArrayList<>();
        Seat temp;
        for (int i = 0; i < size; i++) {
            JSONObject place = (JSONObject) placesToBook.get(i);
            temp = new Seat();
            temp.setRow((byte) place.getInt("row"));
            temp.setNumber((byte) place.getInt("seat"));
            seats.add(temp);
        }

        HttpSession session = request.getSession();
        UserSessionService sessionService  = new UserSessionService();
        String login = sessionService.getLoggedInUser(session);

        UserPersonService userService = new UserPersonService();
        long userId = userService.getUserIdByLogin(login);

        ReservationService service = new ReservationService();
        try {
            service.reserve(userId, sessionId, seats);
        } catch (ReservationException e) {
            System.out.println(e);
            response.sendError(500);
            return;
        }
    }
}

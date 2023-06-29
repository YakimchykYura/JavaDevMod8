package org.example;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(value = "/time")
public class TimeServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        String timeZone = request.getParameter("timezone");

        ZoneId zoneId;

        if (timeZone != null && !timeZone.isEmpty()) {
            zoneId = ZoneId.of(timeZone.replace(" ","+"));
        } else {
            zoneId = ZoneId.of("UTC");
        }
        ZonedDateTime currentTime = ZonedDateTime.now(java.time.Clock.system(zoneId));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
        String formattedTime = currentTime.format(formatter);

        response.getWriter().write("<p>"+ formattedTime +"</p>");

        response.getWriter().close();
    }
}

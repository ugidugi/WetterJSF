package servlets;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static servlets.IndexServlet.services;
/**
 * Сервлет для отбражения данных на страце с осадками
 * @author demijolla
 */
@WebServlet(urlPatterns = {"/precipitation"})
public class PrecipitationServlet extends HttpServlet {
 private String dateStart;
    private String dateEnd;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{ 
        request.getRequestDispatcher("precipitation.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        dateStart = request.getParameter("start");
        dateEnd = request.getParameter("end");
        services.getWd().createWeatherSegment(dateStart, dateEnd,7);
        ServletOutputStream sos = response.getOutputStream();
        response.setHeader("Content-Disposition", "attachment;filename="+services.getWd().getFileWeatherSegment().getAbsolutePath());
        long lenght = services.getWd().getFileWeatherSegment().length();
        response.addHeader("Content-Length", String.valueOf(lenght));
        response.setContentType("application/download");
        FileInputStream fileInputStream = new FileInputStream(services.getWd().getFileWeatherSegment());
        int i;
        while((i=fileInputStream.read())!=-1){
                sos.write(i);
        }
        fileInputStream.close();
        sos.close();
        RequestDispatcher dispatcher = request.getRequestDispatcher("/precipitation.jsp");
        dispatcher.forward(request, response);
    }
}
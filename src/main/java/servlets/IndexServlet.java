
package servlets;

import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import queue.SetQueue;
import services.Services;
/**
 * Главный сервлет, который запускает работу всей программы и потоков
 * @author demijolla
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    private String dateStart;
    private String dateEnd;
    public static Services services = null;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{ 
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        dateStart = request.getParameter("start");
        dateEnd = request.getParameter("end");
        services.getWd().createWeatherSegment(dateStart, dateEnd,0);
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("/page.jsp");
        dispatcher.forward(request, response);
    }
    @Override
    public void init(){
        if(services!=null){}
        else
            services = new Services();
    }
}


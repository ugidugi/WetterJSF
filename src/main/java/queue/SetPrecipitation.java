package queue;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import logic.Weather;
import services.Services;


/**
 * Данный класс - отдельный поток, в определенный период перезаписывает поле осадков в методе Services
 * @author demijolla
 */
public class SetPrecipitation implements Runnable{
    private Thread thr;
    private Services services;
    public SetPrecipitation(Services services){
        this.services = services;
        thr = new Thread(this);
        thr.start();
    }
    @Override
    public void run() {
       try{
           double precipitation=0;
            for(;;){
                Weather weather = new Weather();
                Calendar calendar = Calendar.getInstance();
                Date endDate = calendar.getTime();
                calendar.add(calendar.DATE, -1);
                Date startDate = calendar.getTime();  
                weather.setDate(dateFormatForGraf(startDate));
                precipitation = services.getWd().getPrecipitationForDay
                    (dateFormatForWd(startDate), dateFormatForWd(endDate));
                weather.setPrecipitation(precipitation);
                services.getPrecipitationList().add(weather);
                if(services.getQueueList().size()>30){
                    services.getQueueList().remove(0);
                }
                Thread.sleep(86000000);
            }
       }catch(Exception e){
           
       }
    }
    
    private String dateFormatForWd(Date date){
        SimpleDateFormat c = new SimpleDateFormat("yyyy-MM-dd");
        String str = c.format(date) +" 00:00:00";
        return str;
    }
    private String dateFormatForGraf(Date date){
        SimpleDateFormat c = new SimpleDateFormat("dd.MM");
        String str = c.format(date);
        return str;
    }
}

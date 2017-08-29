
package queue;

import dao.WeatherDao;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import logic.Weather;
import services.Services;

/**
 * Данный класс - отдельный поток, перезаписывает очередь погоды для графиков и
 * среднюю температуру в методе Services
 * @author demijolla
 */
public class SetQueue implements Runnable{
    private Thread thr;
    private Services services;
    private WeatherDao wd;
    
    
    public SetQueue(Services services){
        thr = new Thread(this);
        this.services = services;
        wd = services.getWd();
        thr.start();
    }

    @Override
    public void run() {
        avageWeatherPerDayCreate();
        try{
        Weather weather;
        Weather preWeather = new Weather();
        preWeather.setDate("1");
            for(;;){                
                weather = wd.createWeather();
                if(!weather.getDate().equals(preWeather.getDate())){
                    services.getQueueList().add(weather);
                    preWeather = weather;
                    if(services.getQueueList().size()>40){
                        services.getQueueList().remove(0);
                    }
                    Thread.sleep(10800000);
                        
                }else{
                    
                    Thread.sleep(10800000);
                }
                avageWeatherPerDayCreate();
            }
        }catch(Exception e){
        }
    }
    
    private void avageWeatherPerDayCreate(){
        try{
        double speedWind=0;
        double temperature=0;
        double pressure=0;
        double radiation=0;
        double precipitation=0;
        int directionWind=0;
        int humidity=0;
        
        services.getWd().getAllWeatherPerDay();
        ArrayList<Weather> list = services.getWd().getAllWeatherPerDay();
        for(Weather weather: list){
            speedWind+=weather.getSpeedWind();
            directionWind+=weather.getDirectionWind();
            temperature+=weather.getTemperature();
            humidity+=weather.getHumidity();
            radiation+=weather.getRadiation();
            pressure+=weather.getPressure();
            precipitation+=weather.getPrecipitation();
        }
        int n = list.size();
        BigDecimal dbSpeedWind = new BigDecimal((speedWind/n)).setScale(1, RoundingMode.UP);
        BigDecimal dbTemperature = new BigDecimal((temperature/n)).setScale(1, RoundingMode.UP);
        BigDecimal dbRadiation = new BigDecimal((radiation/n)).setScale(1, RoundingMode.UP);
        BigDecimal dbPressure = new BigDecimal((pressure/n)).setScale(1, RoundingMode.UP);
        BigDecimal dbPrecipitation = new BigDecimal((precipitation/n)).setScale(1, RoundingMode.UP);
        services.getAvageWeatherPerDay().setSpeedWind(dbSpeedWind.doubleValue());
        services.getAvageWeatherPerDay().setDirectionWind(directionWind/n);
        services.getAvageWeatherPerDay().setTemperature(dbTemperature.doubleValue());
        services.getAvageWeatherPerDay().setHumidity(humidity/n);
        services.getAvageWeatherPerDay().setRadiation(dbRadiation.doubleValue());
        services.getAvageWeatherPerDay().setPressure(dbPressure.doubleValue());
        services.getAvageWeatherPerDay().setPrecipitation(dbPrecipitation.doubleValue());
        }catch(Exception e){
            
        }
        
    }
}

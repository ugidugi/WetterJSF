

package services;

import dao.WeatherDao;
import java.util.ArrayList;
import logic.Weather;
import queue.SetPrecipitation;
import queue.SetQueue;
import queue.SetWeatherToday;

/**
 * Вспомагательный класс, который служит для хранения всех данных погоды, взятой
 * из базы данных. Из него сервлеты отправляют на страницу данные
 * @author demijolla
 */
public class Services {
    private WeatherDao wd;
    private ArrayList<Weather> queueList;
    private ArrayList<Weather> precipitationList;
    private Weather weatherToday;
    private Weather avageWeatherPerDay;
    private SetQueue sq;
    private SetWeatherToday swt;
    private SetPrecipitation setPrecip;

    public Services() {
        wd = new WeatherDao();
        precipitationList = wd.startPrecipitation();
        queueList = wd.startQueue();
        weatherToday = wd.createWeather();
        avageWeatherPerDay = new Weather();
        sq = new SetQueue(this);
        swt = new SetWeatherToday(this);
        setPrecip = new SetPrecipitation(this);
    }

    public WeatherDao getWd() {
        return wd;
    }

    public Weather getWeatherToday() {
        return  swt.getWeathertoday();
    }

    public ArrayList<Weather> getQueueList() {
        return queueList;
    }   

    public Weather getAvageWeatherPerDay() {
        return avageWeatherPerDay;
    }

    public ArrayList<Weather> getPrecipitationList() {
        return precipitationList;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queue;

import logic.Weather;
import services.Services;

/**
 * Данный класс - отдельный поток, в определенное время перезаписывает погоду в 
 * данный момент в методе Services
 * @author demijolla
 */
public class SetWeatherToday implements Runnable{
    private Thread thr;
    private Services services;
    private Weather weathertoday;
    
    public SetWeatherToday(Services services){
        this.services = services;
        thr = new Thread(this);
        thr.start();
    }
    @Override
    public void run() {
        try{
            for(;;){
            weathertoday = services.getWd().createWeather();
            Thread.sleep(50000);
            }
        }catch(Exception e){
            
        }
    }

    public Weather getWeathertoday() {
        return weathertoday;
    }
    
}

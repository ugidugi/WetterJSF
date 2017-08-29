
package logic;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Класс задаёт логику объекта погоды. Данный клас определяет так же его структуру
 * @author demijolla
 */
public class Weather implements Serializable{
    private String date;
    private int directionWind;
    private double speedWind;
    private double temperature;
    private int humidity;
    private double radiation;
    private double pressure;
    private double precipitation;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDirectionWind() {
        return directionWind;
    }

    public void setDirectionWind(int directionWind) {
        this.directionWind = directionWind;
    }

    public double getSpeedWind() {
        return speedWind;
    }

    public void setSpeedWind(double speedWind) {
        this.speedWind = speedWind;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getRadiation() {
        return radiation;
    }

    public void setRadiation(double radiation) {
        this.radiation = radiation;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(double precipitation) {
        this.precipitation = precipitation;
    }

    public Weather() {
    }
    public String getGrafDate(){
        
        char[]ch = {date.charAt(8),date.charAt(9),'.',date.charAt(5),
            date.charAt(6),' ',date.charAt(11),date.charAt(12),date.charAt(13),
            date.charAt(14),date.charAt(15)};
        String grafDate = new String(ch);
        return grafDate;
    }
}

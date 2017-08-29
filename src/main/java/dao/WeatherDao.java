
package dao;

import DBConnection.DBConnection;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.Weather;
import reporting.CreateReport;

/**
 * Eine Klasse für die Arbeit mit der Datenbank. Diese besteht aus 
 * unterschiedliche Methoden, die mit der Hilfe einer SQL-Anfragen 
 * ein Datenabruf macht.
 */
public class WeatherDao {
    private Statement statement = null;
    private ResultSet resultSet = null;
    private File fileWeatherSegment;
    private final DBConnection connection;
    private ArrayList<Weather> avageWeatherList;
    private Weather avageWeatherPerDay;
   
    public WeatherDao(){       
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        }
         connection = new DBConnection();
         avageWeatherPerDay = new Weather();
    }
    
    /**
     *  Diese Methode  достает из бд последнюю записанную в неё погоду
     *  @return 
     */
    public Weather createWeather(){
        Weather weather = null;
        try {
            statement = connection.getConnection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM dbase.weather where date=(SELECT MAX(date) from dbase.weather)");
            while(resultSet.next()){
                weather = new Weather();
                weather.setDate(resultSet.getString("date"));
                weather.setDirectionWind(resultSet.getInt("directionWind"));
                weather.setSpeedWind(resultSet.getDouble("speedWind"));
                weather.setTemperature(resultSet.getDouble("temperature"));
                weather.setHumidity(resultSet.getInt("humidity"));
                weather.setRadiation(resultSet.getDouble("radiation"));
                weather.setPressure(resultSet.getDouble("pressure"));
                weather.setPrecipitation(resultSet.getDouble("precipitation"));
            }
        } catch (Exception ex) {
        }finally{
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException ex) {
               
            }
        }
        return weather;
    }
    
    /**
     * Метод достает из бд максимальную скорость ветра
     * @return 
     */
    public String maxSpeedWind(){
        String str = null;
        double maxSpeedWind = 0;
        String date = null;
        try {
            statement = connection.getConnection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM dbase.weather where speedwind=(SELECT MAX(speedwind) from dbase.weather)");
            while(resultSet.next()){
                date = resultSet.getString("date");
                maxSpeedWind = resultSet.getDouble("speedwind");
            }
        } catch (SQLException ex) {
        }finally{
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException ex) {
               
            }
        }
        str = "<b>"+Double.toString(maxSpeedWind) + " m/s</b><br>" + date.substring(0, 10);
        return str;
    }
    
    /**
     * Метод достает из бд минимальну скорость ветра
     * @return 
     */
    public String minSpeedWind(){
        String str = null;
        double maxSpeedWind = 0;
        String date = null;
        try {
            statement = connection.getConnection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM dbase.weather where speedwind=(SELECT MIN(speedwind) from dbase.weather)");
            while(resultSet.next()){
                date = resultSet.getString("date");
                maxSpeedWind = resultSet.getDouble("speedwind");
            }
        } catch (Exception ex) {
        }finally{
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException ex) {
               
            }
        }
        str = "<b>"+Double.toString(maxSpeedWind) + " m/s</b><br>" + date.substring(0, 10);
        return str;
    }
    
 
    /**
     * Метод достает из бд максимальную температуру
     * @return 
     */
    public String maxTemperature(){
        String str = null;
        double maxSpeedWind = 0;
        String date = null;
        try {
            statement = connection.getConnection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM dbase.weather where temperature=(SELECT MAX(temperature) from dbase.weather)");
            while(resultSet.next()){
                date = resultSet.getString("date");
                maxSpeedWind = resultSet.getDouble("temperature");
            }
        } catch (SQLException ex) {
        }finally{
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException ex) {
               
            }
        }
        str = "<b>"+Double.toString(maxSpeedWind) + " °C</b><br>" + date.substring(0, 10);
        return str;
    }
    /**
     * Метод достает из бд минимальную температуру
     * @return 
     */
    public String minTemperature(){
        String str = null;
        double maxSpeedWind = 0;
        String date = null;
        try {
            statement = connection.getConnection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM dbase.weather where temperature=(SELECT MIN(temperature) from dbase.weather)");
            while(resultSet.next()){
                date = resultSet.getString("date");
                maxSpeedWind = resultSet.getDouble("temperature");
            }
        } catch (SQLException ex) {
        }finally{
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException ex) {
               
            }
        }
        str = "<b>"+Double.toString(maxSpeedWind) + " °C</b><br>" + date.substring(0, 10);
        return str;
    }
    /**
     * Метод достает из бд максимальную влажность
     * @return 
     */
    public String maxHumidity(){
        String str = null;
        int maxSpeedWind = 0;
        String date = null;
        try {
            statement = connection.getConnection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM dbase.weather where humidity=(SELECT MAX(humidity) from dbase.weather)");
            while(resultSet.next()){
                date = resultSet.getString("date");
                maxSpeedWind = resultSet.getInt("humidity");
            }
        } catch (SQLException ex) {
        }finally{
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException ex) {
               
            }
        }
        str = "<b>"+Double.toString(maxSpeedWind) + "%</b><br>" + date.substring(0, 10);
        return str;
    }
    /**
     * Метод достает из бд минимальную влажность
     * @return 
     */
    public String minHumidity(){
              String str = null;
        int maxSpeedWind = 0;
        String date = null;
        try {
            statement = connection.getConnection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM dbase.weather where humidity=(SELECT MIN(humidity) from dbase.weather)");
            while(resultSet.next()){
                date = resultSet.getString("date");
                maxSpeedWind = resultSet.getInt("humidity");
            }
        } catch (SQLException ex) {
        }finally{
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException ex) {
               
            }
        }
        str = "<b>"+Double.toString(maxSpeedWind) + "%</b><br>" + date.substring(0, 10);
        return str;
    }
    /**
     * Метод достает из бд максимальный уровень солнечного излучения
     * @return 
     */
    public String maxRadiation(){
        String str = null;
        double maxSpeedWind = 0;
        String date = null;
        try {
            statement = connection.getConnection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM dbase.weather where radiation=(SELECT MAX(radiation) from dbase.weather)");
            while(resultSet.next()){
                date = resultSet.getString("date");
                maxSpeedWind = resultSet.getDouble("radiation");
            }
        } catch (SQLException ex) {
        }finally{
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException ex) {
               
            }
        }
        str = "<b>"+Double.toString(maxSpeedWind) + " W/m2</b><br>" + date.substring(0, 10);
        return str;
    }
    /**
     * Метод достает из бд минимальный уровень солнечного излучения
     * @return 
     */
    public String minRadiation(){
        String str = null;
        double maxSpeedWind = 0;
        String date = null;
        try {
            statement = connection.getConnection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM dbase.weather where radiation=(SELECT MIN(radiation) from dbase.weather)");
            while(resultSet.next()){
                date = resultSet.getString("date");
                maxSpeedWind = resultSet.getDouble("radiation");
            }
        } catch (SQLException ex) {
        }finally{
           try {
               resultSet.close();
               statement.close();
           } catch (SQLException ex) {
           }
        }
        str ="<b>"+Double.toString(maxSpeedWind) + " W/m2</b><br>" + date.substring(0, 10);
        return str;
    }
    /**
     * Метод достает из бд максимальное давление
     * @return 
     */
    public String maxPressure(){
        String str = null;
        double maxSpeedWind = 0;
        String date = null;
        try {
            statement = connection.getConnection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM dbase.weather where pressure=(SELECT MAX(pressure) from dbase.weather)");
            while(resultSet.next()){
                date = resultSet.getString("date");
                maxSpeedWind = resultSet.getDouble("pressure");
            }
        } catch (SQLException ex) {
        }finally{
           try {
               resultSet.close();
               statement.close();
           } catch (SQLException ex) {
           }
        }
        str = "<b>"+Double.toString(maxSpeedWind) + " hPa</b><br>" + date.substring(0, 10);
        return str;
    }
    /**
     * Метод достает из бд минимальное давление
     * @return 
     */
    public String minPressure(){
        String str = null;
        double maxSpeedWind = 0;
        String date = null;
        try {
            statement = connection.getConnection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM dbase.weather where pressure=(SELECT MIN(pressure) from dbase.weather)");
            while(resultSet.next()){
                date = resultSet.getString("date");
                maxSpeedWind = resultSet.getDouble("pressure");
            }
        } catch (Exception ex) {
        }finally{
           try {
               resultSet.close();
               statement.close();
           } catch (Exception ex) {
           }
        }
        str = "<b>"+Double.toString(maxSpeedWind) + " hPa</b><br>" + date.substring(0, 10);
        return str;
    }
    /**
     * Метод достает из бд максимальный уровень осадков
     * @return 
     */
    public String maxPrecipitation(){
        String str = null;
        double maxSpeedWind = 0;
        String date = null;
        try {
            statement = connection.getConnection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM dbase.weather where precipitation=(SELECT MAX(precipitation) from dbase.weather)");
            while(resultSet.next()){
                date = resultSet.getString("date");
                maxSpeedWind = resultSet.getDouble("precipitation");
            }
        } catch (Exception ex) {
        }finally{
           try {
               resultSet.close();
               statement.close();
           } catch (Exception ex) {
           }
        }
        str = "<b>"+Double.toString(maxSpeedWind) + " mm</b><br>" + date.substring(0, 10);
        return str;
    }
    /**
     * Метод достает из бд минимальный уровень осадков
     * @return 
     */
    public String minPrecipitation(){
        String str = null;
        double maxSpeedWind = 0;
        String date = null;
        try {
            statement = connection.getConnection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM dbase.weather where precipitation=(SELECT MIN(precipitation) from dbase.weather)");
            while(resultSet.next()){
                date = resultSet.getString("date");
                maxSpeedWind = resultSet.getDouble("precipitation");
            }
        } catch (Exception ex) {
        }finally{
           try {
               resultSet.close();
               statement.close();
           } catch (Exception ex) {
           }
        }
        str = "<b>"+Double.toString(maxSpeedWind) + " mm</b><br>" + date.substring(0, 10);
        return str;
    }
    /**
     * Метод достает выборку погоды за день для отображение среней погоды за день
     * @return 
     */
    public ArrayList<Weather> getAllWeatherPerDay(){
       try {
            Weather weather;
             avageWeatherList = new ArrayList<>();
            statement = connection.getConnection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM dbase.weather" +
                   " WHERE DATE(date) BETWEEN '"+dateFormat()+" 00:00:00' AND (SELECT MAX(date) from dbase.weather)");
           while(resultSet.next()){
               weather = new Weather();
               weather.setDate(resultSet.getString("date"));
               weather.setDirectionWind(resultSet.getInt("directionWind"));
               weather.setSpeedWind(resultSet.getDouble("speedWind"));
               weather.setTemperature(resultSet.getDouble("temperature"));
               weather.setHumidity(resultSet.getInt("humidity"));
               weather.setRadiation(resultSet.getDouble("radiation"));
               weather.setPressure(resultSet.getDouble("pressure"));
               weather.setPrecipitation(resultSet.getDouble("precipitation"));
               avageWeatherList.add(weather);
           }          
           
       } catch (Exception ex) {
       }finally{
           try{
           statement.close();
           resultSet.close();
           }catch(Exception e){
               
           }
       }
       return  avageWeatherList;
    }
    
    
    public WeatherDao(DBConnection connection) {
        this.connection = connection;
    }
    
    /**
     * Метод нужен получения выборки погоды, которая будет записана в скаченный файл.
     * Методу передаются три параметра: начальную дату, конечную дату и номер
     * для определения записываемых данных(1 - все данные, 2 - скорость ветра и т.д.)
     * @param dateStart
     * @param dateEnd
     * @param n
     * @throws IOException 
     */
    public void createWeatherSegment(String dateStart,String dateEnd,int n) throws IOException{
        CreateReport cr = new CreateReport(dateStart, dateEnd, n);
        Weather weather;
        try {
            statement = connection.getConnection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM dbase.weather\n" +
            " WHERE DATE(date) BETWEEN '"+dateStart+"' AND '"+dateEnd+"'");
            while(resultSet.next()){
                weather = new Weather();
                weather.setDate(resultSet.getString("date"));
                weather.setDirectionWind(resultSet.getInt("directionWind"));
                weather.setSpeedWind(resultSet.getDouble("speedWind"));
                weather.setTemperature(resultSet.getDouble("temperature"));
                weather.setHumidity(resultSet.getInt("humidity"));
                weather.setRadiation(resultSet.getDouble("radiation"));
                weather.setPressure(resultSet.getDouble("pressure"));
                weather.setPrecipitation(resultSet.getDouble("precipitation"));
                writeData(cr, n, weather);
            }
        } catch (SQLException ex) {
        }finally{
           try {
               resultSet.close();
               statement.close();
           } catch (SQLException ex) {
           }
        }
        fileWeatherSegment = cr.getFile();
    }
    /**
     * Вспомагательный метод для метода createWeatherSegment, который непосредственно
     * записывает полученные данные в файл с отчетом, который передается клиенту.
     * @param cr
     * @param n
     * @param weather 
     */   
    private void writeData(CreateReport cr, int n, Weather weather){
        if(n==0){
            cr.whriteWeatherAllInFile(weather);   
        }
        if(n==1){
            cr.whriteWeatherSpeedWindInFile(weather);   
        }
        if(n==2){
            cr.whriteWeatherDirectionWindInFile(weather);   
        }
        if(n==3){
            cr.whriteWeatherTemperatureInFile(weather);   
        }
        if(n==4){
            cr.whriteWeatherHumidityInFile(weather);   
        }
        if(n==5){
            cr.whriteWeatherRadiationInFile(weather);   
        }
        if(n==6){
            cr.whriteWeatherPressureInFile(weather);   
        }
        if(n==7){
            cr.whriteWeatherPrecipitationInFile(weather);   
        }
   }
    public File getFileWeatherSegment() {
        return fileWeatherSegment;
    }    
    private String dateFormat(){
        SimpleDateFormat c = new SimpleDateFormat("yyyy-MM-dd");
        return c.format(new Date());
    }
    /**
     * Метод служит для выборки всех осадков, который выпали за день.
     * @param dateStart
     * @param dateEnd
     * @return 
     */
    public double getPrecipitationForDay(String dateStart, String dateEnd){
        double d = 0;
        try{
            statement = connection.getConnection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM dbase.weather\n" +
            " WHERE DATE(date) BETWEEN '"+dateStart+"' AND '"+dateEnd+"'");
            while(resultSet.next()){
                d = (int)(d+resultSet.getDouble("precipitation"));
            }
        }catch(Exception e){
            
        }finally{
           try {
               resultSet.close();
               statement.close();
           } catch (SQLException ex) {
           }
        }
        return d;
    }
    
    public ArrayList<Weather> startQueue(){
       try {
            Weather weather;
            avageWeatherList = new ArrayList<>();
            statement = connection.getConnection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM dbase.weather" +
                   " WHERE DATE(date) BETWEEN '"+dateFormat()+" 00:00:00' AND '"+" 00:00:00' from dbase.weather)");
           while(resultSet.next()){
               weather = new Weather();
               weather.setDate(resultSet.getString("date"));
               weather.setDirectionWind(resultSet.getInt("directionWind"));
               weather.setSpeedWind(resultSet.getDouble("speedWind"));
               weather.setTemperature(resultSet.getDouble("temperature"));
               weather.setHumidity(resultSet.getInt("humidity"));
               weather.setRadiation(resultSet.getDouble("radiation"));
               weather.setPressure(resultSet.getDouble("pressure"));
               weather.setPrecipitation(resultSet.getDouble("precipitation"));
               avageWeatherList.add(weather);
           }          
           
       } catch (Exception ex) {
       }finally{
           try{
           statement.close();
           resultSet.close();
           }catch(Exception e){
               
           }
       }
       return  avageWeatherList;
    }
    public ArrayList<Weather> startPrecipitation(){
       try {
            Weather weather;
            avageWeatherList = new ArrayList<>();
            statement = connection.getConnection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM dbase.weather" +
                   " WHERE DATE(date) BETWEEN '"+dateFormat()+" 00:00:00' AND (SELECT MAX(date) from dbase.weather)");
           while(resultSet.next()){
               weather = new Weather();
               weather.setDate(resultSet.getString("date"));
               weather.setDirectionWind(resultSet.getInt("directionWind"));
               weather.setSpeedWind(resultSet.getDouble("speedWind"));
               weather.setTemperature(resultSet.getDouble("temperature"));
               weather.setHumidity(resultSet.getInt("humidity"));
               weather.setRadiation(resultSet.getDouble("radiation"));
               weather.setPressure(resultSet.getDouble("pressure"));
               weather.setPrecipitation(resultSet.getDouble("precipitation"));
               avageWeatherList.add(weather);
           }          
           
       } catch (Exception ex) {
       }finally{
           try{
           statement.close();
           resultSet.close();
           }catch(Exception e){
               
           }
       }
       return  avageWeatherList;
    }
    
}

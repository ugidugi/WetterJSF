
package reporting;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.Weather;

/**
 * Класс создает файл с отчетом для скачивания клиента
 * @author demijolla
 */
public class CreateReport {
    private File file;
    private FileWriter fileWriter;
    /**
     * Метод создает файл для записи в него отчетов и передает его классу WeatherDao
     * @param dateStart
     * @param dateEnd
     * @param n
     * @throws IOException 
     */
    public CreateReport(String dateStart,String dateEnd, int n) throws IOException{
        file = new File("D:/Report_Weather_"+dateStart+"_"+dateEnd+".txt");        
        if(!file.exists()){
        try {
            file.createNewFile();
        } catch (IOException ex) {
            
        }
        }
        fileWriter = new FileWriter(file);
        writeHead(fileWriter, n);
    }
    
    /**
     * Метод записывает всю погоду в файл
     * @param weather 
     */
    public void whriteWeatherAllInFile(Weather weather){
        try {
            fileWriter.write(weather.getDate()+"\t"+weather.getDirectionWind()
                    +"\t"+weather.getSpeedWind()+"\t"+weather.getTemperature()+"\t"
                    +weather.getHumidity()+"\t"+weather.getRadiation()
                    +"\t"+weather.getPressure()+"\t"+weather.getPrecipitation()+"\n");
            fileWriter.flush();
        } catch (IOException ex) {
            Logger.getLogger(CreateReport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Метод записывает нарпвление ветра в файл
     * @param weather 
     */
    public void whriteWeatherDirectionWindInFile(Weather weather){
        try {
            fileWriter.write(weather.getDate()+"\t"+weather.getDirectionWind()+"\n");
            fileWriter.flush();
        } catch (IOException ex) {
            Logger.getLogger(CreateReport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Метод записывает скорость ветра в файл
     * @param weather 
     */    
    public void whriteWeatherSpeedWindInFile(Weather weather){
        try {
            fileWriter.write(weather.getDate()+"\t"+weather.getSpeedWind()+"\n");
            fileWriter.flush();
        } catch (IOException ex) {
            Logger.getLogger(CreateReport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Метод записывает температуру в файл
     * @param weather 
     */    
    public void whriteWeatherTemperatureInFile(Weather weather){
        try {
            fileWriter.write(weather.getDate()+"\t"+weather.getTemperature()+"\n");
            fileWriter.flush();
        } catch (IOException ex) {
            Logger.getLogger(CreateReport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Метод записывает влажность в файл
     * @param weather 
     */
    public void whriteWeatherHumidityInFile(Weather weather){
        try {
            fileWriter.write(weather.getDate()+"\t"+weather.getHumidity()+"\n");
            fileWriter.flush();
        } catch (IOException ex) {
            Logger.getLogger(CreateReport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Метод записывает солнечное излучение в файл
     * @param weather 
     */
    public void whriteWeatherRadiationInFile(Weather weather){
        try {
            fileWriter.write(weather.getDate()+"\t"+weather.getRadiation()+"\n");
            fileWriter.flush();
        } catch (IOException ex) {
            Logger.getLogger(CreateReport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Метод записывает давление в файл
     * @param weather 
     */
    public void whriteWeatherPressureInFile(Weather weather){
        try {
            fileWriter.write(weather.getDate()+"\t"+weather.getPressure()+"\n");
            fileWriter.flush();
        } catch (IOException ex) {
            Logger.getLogger(CreateReport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Метод записывает осадки в файл
     * @param weather 
     */
    public void whriteWeatherPrecipitationInFile(Weather weather){
        try {
            fileWriter.write(weather.getDate()+"\t"+weather.getPrecipitation()+"\n");
            fileWriter.flush();
        } catch (IOException ex) {
            Logger.getLogger(CreateReport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public File getFile() {
        return file;
    }
    /**
     * Метод нужен для создание определенной шапки в файл
     * @param fileWriter
     * @param n 
     */
    public void writeHead(FileWriter fileWriter,int n){
        String head;
        try{
            if(n==0){
                head = "Date:"+"\t"+"   "+"Time:"+"\t"+"\t"+"°"+"\t"+"m/s"+"\t"+
                        "°C"+"\t"+"\t"+"%"+"\t"+"W/m2"+"\t"+"hPa"+"\t"+"mm"+"\n";
                fileWriter.write(head);
                fileWriter.flush();
            }
            if(n==1){
                head = "Date:"+"\t"+"   "+"Time:"+"\t"+"\t"+"°"+"\n";
                fileWriter.write(head);
                fileWriter.flush();
            }
            if(n==2){
                head = "Date:"+"\t"+"   "+"Time:"+"\t"+"\t"+"m/s"+"\n";
                fileWriter.write(head);
                fileWriter.flush();     
            }
            if(n==3){
                head = "Date:"+"\t"+"   "+"Time:"+"\t"+"\t"+"°C"+"\n";
                fileWriter.write(head);
                fileWriter.flush(); 
            }
            if(n==4){
                head = "Date:"+"\t"+"   "+"Time:"+"\t"+"\t"+"%"+"\n";
                fileWriter.write(head);
                fileWriter.flush(); 
            }
            if(n==5){
                head = "Date:"+"\t"+"   "+"Time:"+"\t"+"\t"+"W/m2"+"\n";
                fileWriter.write(head);
                fileWriter.flush(); 
            }
            if(n==6){
                head = "Date:"+"\t"+"   "+"Time:"+"\t"+"\t"+"hPa"+"\n";
                fileWriter.write(head);
                fileWriter.flush(); 
            }
            if(n==7){
                head = "Date:"+"\t"+"   "+"Time:"+"\t"+"\t"+"mm"+"\n";
                fileWriter.write(head);
                fileWriter.flush(); 
            }
        }catch(IOException e){
            
        }
    }
}

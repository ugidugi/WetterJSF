
package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;

/**
 * 
 Eine Klasse für die Verbindung mit einer MySQL-Datenbank.
 */
public class DBConnection{
    
    private Connection connection = null;
    private final String dbase = "jdbc:mysql://localhost:3306/dbase?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&characterEncoding=utf-8";
    private final String name = "root";
    private final String password = "1111";
    
    public DBConnection(){
        TimeZone timeZone =  TimeZone.getTimeZone("Europe / Berlin"); 
        TimeZone.setDefault(timeZone); 
        try {
            connection = DriverManager.getConnection(dbase, name, password);
        } catch (SQLException ex) {
        }
    }

    public Connection getConnection() {
        if(connection==null){
            try {
                connection = DriverManager.getConnection(dbase,name,password);
            } catch (SQLException ex) {
            }
        }
        return connection;
    }
    
}

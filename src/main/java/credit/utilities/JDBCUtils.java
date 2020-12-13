package credit.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class JDBCUtils {
    private static String jdbcURL = "jdbc:hsqldb:file:src/main/resources/EVENTS;";
    private static String jdbcUsername = "SA";
    private static String jdbcPassword = "";

    public static Connection getConnection() {
        Connection connection = null;
        try {
        	Class.forName("org.hsqldb.jdbc.JDBCDriver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static void createTable() throws SQLException {
        
        try{
        	Connection connection = JDBCUtils.getConnection(); Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS EVENTS(id VARCHAR(20) NOT NULL, duration VARCHAR(10) ,type VARCHAR(20), host VARCHAR(10), alert BOOLEAN);");
            connection.commit();
            
        }
        catch (Exception e) {
			// TODO: handle exception
        	e.printStackTrace();
		}
    }
    
    public static void dropTable() throws SQLException {
        try {
        	Connection connection = JDBCUtils.getConnection(); Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE EVENTS;");
            connection.commit();
           
        }
        catch (Exception e) {
			// TODO: handle exception
        	e.printStackTrace();
		}
    }
    public static void closeConnection() throws SQLException {
        try {
        	Connection connection = JDBCUtils.getConnection(); Statement statement = connection.createStatement();
            statement.executeUpdate("SHUTDOWN");
            connection.commit();
        }
        catch (Exception e) {
			// TODO: handle exception
        	e.printStackTrace();
		}
    }

}

package credit.dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import credit.bean.Event;
import credit.utilities.JDBCUtils;


public class LogsfilterDAOImpl implements LogsfilterDAO {

    private static final Logger LOGGER  = LogManager.getLogger(LogsfilterDAOImpl.class.getName());



    public void insertEvent(Event event) throws SQLException {
        try{
        	Connection connection = JDBCUtils.getConnection(); 
        	Statement statement = connection.createStatement();
            String update = "INSERT INTO EVENTS VALUES ('" + event.getId() + "','" + event.getDuration()+ "','" +  event.getType()+ "','" +  event.getHost()+ "','" + event.isAlert() + "')";
            statement.executeUpdate(update);
            connection.commit();
            
        }
        catch (Exception e) {
			// TODO: handle exception
        	LOGGER.debug("Error occured in inserting events");
        	e.printStackTrace();
		}
    }


    public void alertedEvents() throws SQLException {
        try{
        	
        	Connection connection = JDBCUtils.getConnection(); 
        	Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM EVENTS WHERE alert = 1");
            while (result.next()){
            	LOGGER.info("---| ID : "+result.getString("id")+" | Host : "+result.getString("host")+" | State : "+result.getString("type")+" | Duration : "+ result.getString("duration")+" |");
            }
        }
        catch (Exception e) {
			// TODO: handle exception
        	LOGGER.debug("Error occured in Alerted events");
        	e.printStackTrace();
		}
    }




}

package credit.credit;


import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import credit.main.App;
import credit.service.LogsfilterServiceImpl;
import credit.utilities.Factory;
import credit.utilities.JDBCUtils;



public class AppTest
{
	
	   @BeforeClass
	    public static void init() throws SQLException, ClassNotFoundException, IOException {
	        JDBCUtils.createTable();
	    }

	    @AfterClass
	    public static void destroy() throws SQLException, ClassNotFoundException, IOException, Exception {
	    	
	        	JDBCUtils.dropTable();
	        	JDBCUtils.closeConnection();
	   
	    }

	    public static int insertEvent() throws Exception {
	    	LogsfilterServiceImpl service = Factory.createLogFilterService();
	    	ArrayList<JSONObject> logList = App.jsonCreation("src\\test\\testfile\\logfile.txt");
	    	service.filterLogs(logList);
	    	
	        try {
	        	Connection connection = JDBCUtils.getConnection(); 
	        	Statement statement = connection.createStatement();
	            ResultSet result = statement.executeQuery("SELECT COUNT(*) as TOTAL FROM EVENTS");
	            if (result.next()) {
	                return result.getInt("TOTAL");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return 0;
	    }
	    

	    public static int getTotalAlertedEvents() {
	        try {
	        	Connection connection = JDBCUtils.getConnection(); 
	        	Statement statement = connection.createStatement();
	            ResultSet result = statement.executeQuery("SELECT COUNT(*) as TOTAL FROM EVENTS WHERE alert = 1");
	            if (result.next()) {
	                return result.getInt("TOTAL");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return 0;
	    }
	    
	    @Test
	    public void parseJSONTest() throws Exception {
	    	ArrayList<JSONObject> logList = App.jsonCreation("src\\test\\testfile\\logfile.txt");
	    	assertEquals(6, logList.size());
	    }
	    
	    @Test
	    public void insertEventTest() throws Exception {
	    	int resultcount=insertEvent();
	    	assertEquals(3, resultcount);
	    	
	    }
	    @Test
	    public void getTotalAlertedEventsTest() {
	    	int resultcount= getTotalAlertedEvents();
	    	assertEquals(2, resultcount);
	    }
}

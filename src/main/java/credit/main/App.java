package credit.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import credit.service.LogsfilterServiceImpl;
import credit.utilities.Factory;
import credit.utilities.JDBCUtils;


public class App 
{
	private static final Logger LOGGER  = LogManager.getLogger(App.class.getName());
    public static void main( String[] args )
    {

    	try {  	
    	LogsfilterServiceImpl service = Factory.createLogFilterService();
    	JDBCUtils.createTable();
    	App.gettingStarted();
    	service.alertedEvents();
    	JDBCUtils.dropTable();
    	JDBCUtils.closeConnection();
    	}
    	catch(Exception e) {
    		LOGGER.debug("Exit");
    	}
    }
    
    public static void gettingStarted(){
    	LogsfilterServiceImpl service = Factory.createLogFilterService();
    	Scanner scanner= new Scanner(System.in);
    	System.out.print("Paste the complete path for logfile : "); 
    	String path = scanner.nextLine();
    	scanner.close();
		ArrayList<JSONObject> jsonArray = App.jsonCreation(path);
        service.filterLogs(jsonArray);

    }   
    public static ArrayList<JSONObject> jsonCreation(String path){
  	  	
  	  	String eachJson="";
    	ArrayList<JSONObject> logList = new ArrayList<JSONObject>();
    	JSONParser parser = new JSONParser();
		try {
			File file = new File(path); 
	        Scanner scanner = new Scanner(new FileInputStream(file));
	        scanner.useDelimiter("}");
	        while(scanner.hasNext()) {
	           eachJson =scanner.next()+"}";
	           JSONObject json = (JSONObject) parser.parse(eachJson);
	           logList.add(json);
	            }
	        LOGGER.info("JSON parsed successfully from log file");
	        scanner.close();
			}
		catch (FileNotFoundException notFound) {
			LOGGER.debug("File not found in the path given");
			App.gettingStarted();
		}
		catch (Exception e) {
			LOGGER.debug("Unexpected error occured");
		}
		return logList;
    }
    

    
}
    

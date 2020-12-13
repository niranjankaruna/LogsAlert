package credit.service;


import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import credit.bean.Event;
import credit.dao.LogsfilterDAOImpl;
import credit.utilities.Factory;


public class LogsfilterServiceImpl implements LogsfilterService{

	private static final Logger LOGGER  = LogManager.getLogger(LogsfilterServiceImpl.class.getName());
	
	public void filterLogs(ArrayList<JSONObject> input) {
		try {
		LogsfilterDAOImpl logDAO= Factory.createLogFilterDAO();
		

		 for(int i=0; i<input.size();i++) {
		 	JSONObject temp= input.get(i);
		 	if(i<input.size()){
		 	for(int j=i+1; j<input.size();j++) {
		 		JSONObject temp1= input.get(j);
		 			if(temp.get("id").equals(temp1.get("id"))){
		 				long t1= (Long) temp.get("timestamp");
		 				long t2 = (Long) temp1.get("timestamp");
		 				double duration= t1 - t2;
		 				duration =Math.abs(duration);
		 				Event e= new Event();
		 				e.setId((String) temp.get("id"));
		 				e.setDuration(duration +" ms");
		 				e.setType((String) temp.get("type"));
		 				e.setHost((String) temp.get("host"));
		 				if(duration >4 ) {
		 					e.setAlert(true);
		 				}
		 				else {
		 					e.setAlert(false);
		 				}
		 				logDAO.insertEvent(e);
		 				break;
		 			}
		 		
		 	}
		 	} 
		 }	
		 LOGGER.info(" Event details parsed from JSON and inserted into database");
		 
	}
		catch(Exception e) {
			 e.printStackTrace();
		}

	}
		
	public void alertedEvents() throws Exception{
		LogsfilterDAOImpl logDAO= Factory.createLogFilterDAO();
		logDAO.alertedEvents();
	}
}

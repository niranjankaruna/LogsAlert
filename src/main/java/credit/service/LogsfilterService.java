package credit.service;

import java.util.ArrayList;
import org.json.simple.JSONObject;


public interface LogsfilterService {

	public  void filterLogs(ArrayList<JSONObject> input);
	public void alertedEvents() throws Exception;
}

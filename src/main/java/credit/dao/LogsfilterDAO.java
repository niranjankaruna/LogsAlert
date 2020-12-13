package credit.dao;

import java.sql.SQLException;

import credit.bean.Event;


public interface LogsfilterDAO {
	
	public void insertEvent(Event event) throws SQLException;
	public void alertedEvents() throws SQLException;
	
}

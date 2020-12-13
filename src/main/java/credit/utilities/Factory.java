package credit.utilities;

import credit.dao.LogsfilterDAOImpl;
import credit.service.LogsfilterServiceImpl;

public class Factory {

	public static LogsfilterServiceImpl createLogFilterService () {
		return new LogsfilterServiceImpl();
	}
	public static LogsfilterDAOImpl createLogFilterDAO () {
		return new LogsfilterDAOImpl();
	}
}

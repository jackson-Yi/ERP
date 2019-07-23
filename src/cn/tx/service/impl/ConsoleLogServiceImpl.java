package cn.tx.service.impl;

import cn.tx.dao.ConsoleLogDao;
import cn.tx.model.ConsoleLog;
import cn.tx.query.ConsoleLogQuery;
import cn.tx.service.ConsoleLogService;

public class ConsoleLogServiceImpl extends BaseServiceImpl<ConsoleLog, ConsoleLogQuery> implements ConsoleLogService {

	
	private ConsoleLogDao consoleLogDao;
	
	public void setConsoleLogDao(ConsoleLogDao consoleLogDao) {
		this.consoleLogDao = consoleLogDao;
		this.baseDao = consoleLogDao;
	}
	
	

}

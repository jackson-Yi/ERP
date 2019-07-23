package cn.tx.dao.impl;

import org.apache.commons.lang.StringUtils;

import cn.tx.dao.ConsoleLogDao;
import cn.tx.model.ConsoleLog;
import cn.tx.query.ConsoleLogQuery;

public class ConsoleLogDaoImpl extends BaseDaoImpl<ConsoleLog, ConsoleLogQuery> implements ConsoleLogDao {

	@Override
	public String createHql(ConsoleLogQuery equery) {
		String hql = "from ConsoleLog t where 1=1 ";
		hql = hql + createHqlCondition(equery) + " order by t.logId desc";
		return hql;
	}

	@Override
	public String createHqlCount(ConsoleLogQuery q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createHqlCondition(ConsoleLogQuery q) {
		String hql = " and entityId = :entityId and t.tableName like :tableName and t.optType like :optType";
		
		return hql;
	}
	

}

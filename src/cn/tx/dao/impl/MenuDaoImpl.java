package cn.tx.dao.impl;

import org.apache.commons.lang.StringUtils;

import cn.tx.dao.MenuDao;
import cn.tx.model.Menu;
import cn.tx.query.MenuQuery;

public class MenuDaoImpl extends BaseDaoImpl<Menu, MenuQuery> implements MenuDao {

	@Override
	public String createHql(MenuQuery equery) {
		String hql = "from Menu t where 1=1 ";
		
		return hql;
	}

	@Override
	public String createHqlCount(MenuQuery q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createHqlCondition(MenuQuery q) {
		// TODO Auto-generated method stub
		return null;
	}
	

}

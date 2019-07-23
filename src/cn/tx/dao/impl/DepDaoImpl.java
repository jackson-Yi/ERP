package cn.tx.dao.impl;

import org.apache.commons.lang.StringUtils;

import cn.tx.dao.DepDao;
import cn.tx.model.Dep;
import cn.tx.query.DepQuery;

public class DepDaoImpl extends BaseDaoImpl<Dep, DepQuery> implements DepDao {

	@Override
	public String createHql(DepQuery q) {
		String hql = "from Dep t where 1=1 ";
		hql = hql + createHqlCondition(q);
		return hql;
	}

	@Override
	public String createHqlCount(DepQuery q) {
		String hql = "select count(depId) from Dep t where 1=1 ";
		hql = hql + createHqlCondition(q);
		return hql;
	}

	@Override
	public String createHqlCondition(DepQuery q) {
		String hql = "";
		if(StringUtils.isNotBlank(q.getName())){
			hql = hql + " and t.name like :name";
		}
		if(StringUtils.isNotBlank(q.getTel())){
			hql = hql + " and t.tel like :tel";
		}
		return hql;
	}

	
	
	

}

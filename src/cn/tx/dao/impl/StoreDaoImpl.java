package cn.tx.dao.impl;

import org.apache.commons.lang.StringUtils;

import cn.tx.dao.StoreDao;
import cn.tx.model.Store;
import cn.tx.query.StoreQuery;

public class StoreDaoImpl extends BaseDaoImpl<Store, StoreQuery> implements StoreDao {

	@Override
	public String createHql(StoreQuery equery) {
		String hql = "from Store t where 1=1 ";
		hql = hql + createHqlCondition(equery);
		return hql;
	}

	@Override
	public String createHqlCount(StoreQuery q) {
		String hql = "select count(storeId) from Store t where 1=1 ";
		hql = hql + createHqlCondition(q);
		return hql;
	}

	@Override
	public String createHqlCondition(StoreQuery q) {
		String hql = "";
		if(StringUtils.isNotBlank(q.getName())){
			hql = hql + " and t.name like :name";
		}
		if(StringUtils.isNotBlank(q.getAddress())){
			hql = hql + " and t.address like :address";
		}
		return hql;
	}
	

}

package cn.tx.dao.impl;

import org.apache.commons.lang.StringUtils;

import cn.tx.dao.SupplierDao;
import cn.tx.model.Supplier;
import cn.tx.query.SupplierQuery;

public class SupplierDaoImpl extends BaseDaoImpl<Supplier, SupplierQuery> implements SupplierDao {

	@Override
	public String createHql(SupplierQuery equery) {
		String hql = "from Supplier t where 1=1 ";
		
		return hql;
	}

	@Override
	public String createHqlCount(SupplierQuery q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createHqlCondition(SupplierQuery q) {
		// TODO Auto-generated method stub
		return null;
	}
	

}

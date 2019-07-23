package cn.tx.service.impl;

import java.util.List;

import cn.tx.dao.DepDao;
import cn.tx.model.Dep;
import cn.tx.query.DepQuery;
import cn.tx.service.DepService;

public class DepServiceImpl extends BaseServiceImpl<Dep, DepQuery> implements DepService {

	
	private DepDao depDao;
	
	public void setDepDao(DepDao depDao) {
		this.depDao = depDao;
		//当我们使用公用的dao的时候必须要一个具体的dao赋值给baseDao
		this.baseDao = depDao;
	}
	
	

}

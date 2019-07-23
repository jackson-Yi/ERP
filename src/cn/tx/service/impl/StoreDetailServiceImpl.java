package cn.tx.service.impl;

import cn.tx.dao.StoreDetailDao;
import cn.tx.model.StoreDetail;
import cn.tx.query.StoreDetailQuery;
import cn.tx.service.StoreDetailService;

public class StoreDetailServiceImpl extends BaseServiceImpl<StoreDetail, StoreDetailQuery> implements StoreDetailService {

	
	private StoreDetailDao storeDetailDao;
	
	public void setStoreDetailDao(StoreDetailDao storeDetailDao) {
		this.storeDetailDao = storeDetailDao;
		this.baseDao = storeDetailDao;
	}
	
	

}

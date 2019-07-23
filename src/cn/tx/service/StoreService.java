package cn.tx.service;

import cn.tx.model.Store;
import cn.tx.query.StoreQuery;

public interface StoreService extends BaseService<Store, StoreQuery> {
	
	public void updateInstock(Integer storeId, Integer productId, Integer productNum, Integer orderDetailId);

}

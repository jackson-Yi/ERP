package cn.tx.dao;

import cn.tx.model.OrderDetail;
import cn.tx.query.OrderDetailQuery;

public interface OrderDetailDao extends BaseDao<OrderDetail, OrderDetailQuery> {
	
	public void deleteDetailByOrderId(Integer orderId);
	

}

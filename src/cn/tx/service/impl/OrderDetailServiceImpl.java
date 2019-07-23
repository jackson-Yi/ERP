package cn.tx.service.impl;

import cn.tx.dao.OrderDetailDao;
import cn.tx.model.OrderDetail;
import cn.tx.query.OrderDetailQuery;
import cn.tx.service.OrderDetailService;

public class OrderDetailServiceImpl extends BaseServiceImpl<OrderDetail, OrderDetailQuery> implements OrderDetailService {

	
	private OrderDetailDao orderDetailDao;
	
	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
		this.baseDao = orderDetailDao;
	}
	
	

}

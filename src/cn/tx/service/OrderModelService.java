package cn.tx.service;

import cn.tx.model.Emp;
import cn.tx.model.OrderModel;
import cn.tx.query.OrderModelQuery;

public interface OrderModelService extends BaseService<OrderModel, OrderModelQuery> {
	
	public void submitOrder(OrderModel order);
	
	public void updateAuditOrder(Emp checker, OrderModel order, String note);
	
	public void updateOrder(OrderModel order);
	
	public void updateAssginOrder(OrderModel order);

}

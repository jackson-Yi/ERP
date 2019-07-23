package cn.tx.service.impl;

import java.sql.Timestamp;
import java.util.Date;

import cn.tx.dao.ConsoleLogDao;
import cn.tx.dao.OrderDetailDao;
import cn.tx.dao.OrderModelDao;
import cn.tx.model.ConsoleLog;
import cn.tx.model.Emp;
import cn.tx.model.OrderModel;
import cn.tx.query.OrderModelQuery;
import cn.tx.service.OrderModelService;
import cn.tx.utils.ERPConstants;

public class OrderModelServiceImpl extends BaseServiceImpl<OrderModel, OrderModelQuery> implements OrderModelService {

	
	private OrderModelDao orderModelDao;
	
	private ConsoleLogDao consoleLogDao;
	
	private OrderDetailDao orderDetailDao;
	
	
	
	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
	}

	public void setConsoleLogDao(ConsoleLogDao consoleLogDao) {
		this.consoleLogDao = consoleLogDao;
	}

	public void setOrderModelDao(OrderModelDao orderModelDao) {
		this.orderModelDao = orderModelDao;
		this.baseDao = orderModelDao;
	}

	@Override
	public void submitOrder(OrderModel order) {
		orderModelDao.save(order);
	}

	@Override
	public void updateAuditOrder(Emp checker, OrderModel order, String note) {
		OrderModel order1 = orderModelDao.getObj(order.getOrderId());
		order1.setOrderState(order.getOrderState());
		order1.setCheckTime(new Date());
		order1.setOrderChecker(checker);
		
		//创建一个日志对象
		ConsoleLog cl = new ConsoleLog();
		cl.setEmp(checker);
		cl.setEntityId(order.getOrderId());
		cl.setNote(note);
		cl.setOptTime(new Timestamp(new Date().getTime()));
		cl.setOptType("审核订单");
		cl.setTableName("order_model");
		consoleLogDao.save(cl);		
	}

	@Override
	public void updateOrder(OrderModel order) {
		orderDetailDao.deleteDetailByOrderId(order.getOrderId());
		orderModelDao.update(order);
	}

	@Override
	public void updateAssginOrder(OrderModel order) {
		OrderModel order1 = orderModelDao.getObj(order.getOrderId());
		order1.setOrderCompleter(order.getOrderCompleter());
		order1.setOrderType(new Integer(ERPConstants.ORDER_TYPE_TRANS));
		order1.setOrderState(new Integer(ERPConstants.ORDER_TYPE_TRANS_BUY));
		
	}
}

package cn.tx.controller;

import java.util.List;

import cn.tx.model.OrderDetail;
import cn.tx.query.OrderDetailQuery;
import cn.tx.service.OrderDetailService;
import cn.tx.utils.Page;

import com.opensymphony.xwork2.ActionContext;

public class OrderDetailAction extends BaseAction {

	private OrderDetailQuery query = new OrderDetailQuery();
	
	public OrderDetailQuery getQuery() {
		return query;
	}

	public void setQuery(OrderDetailQuery query) {
		this.query = query;
	}

	private OrderDetailService orderDetailService;
	
	public OrderDetailService getOrderDetailService() {
		return orderDetailService;
	}

	public void setOrderDetailService(OrderDetailService orderDetailService) {
		this.orderDetailService = orderDetailService;
	}

	public String orderDetail_list(){
		
		ActionContext context = ActionContext.getContext();
		
		Integer pageNo = query.getPageNo();
		if(pageNo == null ){
			query.setPageNo(1);
		}
		
		Page page = orderDetailService.queryObjByCondition(query, exclude);
		
		context.put("page", page);
		return SUCCESS;
	}
	
	public String orderDetail_input(){
		return SUCCESS;
	}
	
}

package cn.tx.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.tx.model.Dep;
import cn.tx.model.Emp;
import cn.tx.model.OrderDetail;
import cn.tx.model.OrderModel;
import cn.tx.model.Product;
import cn.tx.model.Supplier;
import cn.tx.query.OrderModelQuery;
import cn.tx.service.DepService;
import cn.tx.service.OrderModelService;
import cn.tx.service.ProductService;
import cn.tx.service.SupplierService;
import cn.tx.utils.ERPConstants;
import cn.tx.utils.Page;

import com.opensymphony.xwork2.ActionContext;

public class TranOrderAction extends BaseAction {
	
	
	private OrderModelQuery query = new OrderModelQuery();
	
	
	private OrderModel order = new OrderModel();
	
	private SupplierService supplierService;
	
	

	private ProductService productService;
	
	private DepService depService;
	
	public void setDepService(DepService depService) {
		this.depService = depService;
	}
	
	
	
	
	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	

	public SupplierService getSupplierService() {
		return supplierService;
	}

	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}



	public OrderModel getOrder() {
		return order;
	}

	public void setOrder(OrderModel order) {
		this.order = order;
	}

	public OrderModelQuery getQuery() {
		return query;
	}

	public void setQuery(OrderModelQuery query) {
		this.query = query;
	}

	private OrderModelService orderModelService;
	
	
	
	
	
	public OrderModelService getOrderModelService() {
		return orderModelService;
	}

	public void setOrderModelService(OrderModelService orderModelService) {
		this.orderModelService = orderModelService;
	}



	
	
	
	
	public String tranOrder_taskList(){
		
		ActionContext context = ActionContext.getContext();
		
		Integer pageNo = query.getPageNo();
		if(pageNo == null ){
			query.setPageNo(1);
		}
		
		Page page = orderModelService.queryObjByCondition(query, exclude);
		
		context.put("page", page);
		
		
		return SUCCESS;
	}
	
	
	public String tranOrder_taskDetail(){
		order = orderModelService.getObj(order.getOrderId());
		
		Dep dep = depService.getObj(2);
		ActionContext context = ActionContext.getContext();
		context.put("dep", dep);
		
		return SUCCESS;
	}
	public String tranOrder_taskDetailbuying(){
		order = orderModelService.getObj(order.getOrderId());
		
		Dep dep = depService.getObj(2);
		ActionContext context = ActionContext.getContext();
		context.put("dep", dep);
		
		return SUCCESS;
	}
	public void ajax_tranOrder_assginOrder() throws IOException{
		orderModelService.updateAssginOrder(order);
		
		response.getWriter().write("success");
	}
	
	public void ajax_tranOrder_getOrderProduct() throws IOException{
		OrderModel order1 = orderModelService.getObj(order.getOrderId());
		order1.setOrderState(3);
		orderModelService.update(order1);
		
		response.getWriter().write("success");
	}
	public void ajax_tranOrder_finishTranOrder() throws IOException{
		OrderModel order1 = orderModelService.getObj(order.getOrderId());
		order1.setOrderType(3);
		order1.setOrderState(1);
		orderModelService.update(order1);
		
		response.getWriter().write("success");
	}
	
	
	
	public String tranOrder_tasks(){
		
		ActionContext context = ActionContext.getContext();
		
		Map<String, Object> session2 = context.getSession();
		Emp emp = (Emp) session2.get("user");
		query.setCompleter(emp.getEmpId());
		Integer pageNo = query.getPageNo();
		if(pageNo == null ){
			query.setPageNo(1);
		}
		
		Page page = orderModelService.queryObjByCondition(query, exclude);
		
		context.put("page", page);
		
		
		return SUCCESS;
	}
	
	
	public String tranOrder_inList(){
		
		ActionContext context = ActionContext.getContext();
		
		Integer pageNo = query.getPageNo();
		if(pageNo == null ){
			query.setPageNo(1);
		}
		
		Page page = orderModelService.queryObjByCondition(query, exclude);
		
		context.put("page", page);
		
		
		return "store_success";
	}
	public String tranOrder_inDetail(){
	
		order = orderModelService.getObj(query.getOrderId());
		
		return "store_inDetail";
	}
	
}

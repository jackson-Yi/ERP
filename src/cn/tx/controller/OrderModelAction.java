package cn.tx.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.tx.model.Emp;
import cn.tx.model.OrderDetail;
import cn.tx.model.OrderModel;
import cn.tx.model.Product;
import cn.tx.model.Supplier;
import cn.tx.query.OrderModelQuery;
import cn.tx.service.OrderModelService;
import cn.tx.service.ProductService;
import cn.tx.service.SupplierService;
import cn.tx.utils.ERPConstants;
import cn.tx.utils.Page;

import com.opensymphony.xwork2.ActionContext;

public class OrderModelAction extends BaseAction {
	
	
	private OrderModelQuery query = new OrderModelQuery();
	
	
	private OrderModel order = new OrderModel();
	
	private SupplierService supplierService;
	
	private Integer [] productTypeId;
	
	private Integer [] productId;
	
	private Integer [] detailNum;
	
	private Double [] detailPrice;
	
	private String note;
	
	
	
	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	private ProductService productService;
	
	
	
	
	
	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public Integer[] getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(Integer[] productTypeId) {
		this.productTypeId = productTypeId;
	}

	public Integer[] getProductId() {
		return productId;
	}

	public void setProductId(Integer[] productId) {
		this.productId = productId;
	}

	public Integer[] getDetailNum() {
		return detailNum;
	}

	public void setDetailNum(Integer[] detailNum) {
		this.detailNum = detailNum;
	}

	public Double[] getDetailPrice() {
		return detailPrice;
	}

	public void setDetailPrice(Double[] detailPrice) {
		this.detailPrice = detailPrice;
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


	public String orderModel_list(){
		
		ActionContext context = ActionContext.getContext();
		
		Integer pageNo = query.getPageNo();
		if(pageNo == null ){
			query.setPageNo(1);
		}
		
		Page page = orderModelService.queryObjByCondition(query, exclude);
		
		context.put("page", page);
		
		
		return SUCCESS;
	}
	public String orderModel_checklist(){
		
		ActionContext context = ActionContext.getContext();
		
		Integer pageNo = query.getPageNo();
		if(pageNo == null ){
			query.setPageNo(1);
		}
		
		Page page = orderModelService.queryObjByCondition(query, exclude);
		
		context.put("page", page);
		
		
		return SUCCESS;
	}
	public String orderModel_auditText(){
		
		
		
		
		return SUCCESS;
	}
	
	public String orderModel_input(){
		ActionContext context = ActionContext.getContext();
		
		//查询所有的供应商
		List<Supplier> suppliers = supplierService.list();
		context.put("suppliers", suppliers);
		
		return SUCCESS;
	}
	public String orderModel_update(){
		ActionContext context = ActionContext.getContext();
		
		//查询所有的供应商
		List<Supplier> suppliers = supplierService.list();
		context.put("suppliers", suppliers);
		order = orderModelService.getObj(query.getOrderId());
		
		return SUCCESS;
	}
	
	public String orderModel_orderDetail(){
		order = orderModelService.getObj(query.getOrderId());
		return SUCCESS;
	}
	public void ajax_orderModel_submitOrder() throws IOException{
		
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session2 = context.getSession();
		Emp emp = (Emp) session2.get("user");
		Supplier supplier = supplierService.getObj(order.getSupplierId());
		order.setSupplier(supplier);
		order.setOrderNum(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
		order.setOrderCreater(emp);
		order.setCreateTime(new Date());
		order.setOrderType(new Integer(ERPConstants.ORDER_TYPE_BUY));
		order.setOrderState(new Integer(ERPConstants.ORDER_TYPE_BUY_AUDIT));
		int totalNum = 0;
		double totalPrice = 0;
		
		Set<OrderDetail> ods = new HashSet<OrderDetail>();
		for(int i = 0; i< productTypeId.length; i++){
			OrderDetail od = new OrderDetail();
			od.setDetailNum(detailNum[i]);
			od.setDetailPrice(detailPrice[i]);
			Product product = productService.getObj(productId[i]);
			od.setProduct(product);
			od.setDetailNum(detailNum[i]);
			od.setSurplus(detailNum[i]);
			ods.add(od);
			totalNum = totalNum + detailNum[i];
			totalPrice = totalPrice + detailNum[i]*detailPrice[i];
		}
		order.setTotalNum(totalNum);
		order.setTotalPrice(totalPrice);
		order.setDetails(ods);
		orderModelService.submitOrder(order);
		response.getWriter().write("success");
	}
	public void ajax_orderModel_auditOrder() throws IOException{
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session2 = context.getSession();
		Emp emp = (Emp) session2.get("user");
		orderModelService.updateAuditOrder(emp, order, note);
		
		response.getWriter().write("success");
	}
	
	
	public void ajax_orderModel_updateOrder() throws IOException{
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session2 = context.getSession();
		Emp emp = (Emp) session2.get("user");
		
		
		
		Supplier supplier = supplierService.getObj(order.getSupplier().getSupplierId());
		order.setSupplier(supplier);
		//order.setOrderNum(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
		order.setOrderCreater(emp);
		order.setCreateTime(new Date());
		order.setOrderType(new Integer(ERPConstants.ORDER_TYPE_BUY));
		order.setOrderState(new Integer(ERPConstants.ORDER_TYPE_BUY_AUDIT));
		int totalNum = 0;
		double totalPrice = 0;
		
		Set<OrderDetail> ods = new HashSet<OrderDetail>();
		for(int i = 0; i< productTypeId.length; i++){
			OrderDetail od = new OrderDetail();
			od.setDetailNum(detailNum[i]);
			od.setDetailPrice(detailPrice[i]);
			Product product = productService.getObj(productId[i]);
			od.setProduct(product);
			od.setDetailNum(detailNum[i]);
			od.setSurplus(detailNum[i]);
			ods.add(od);
			totalNum = totalNum + detailNum[i];
			totalPrice = totalPrice + detailNum[i]*detailPrice[i];
		}
		order.setTotalNum(totalNum);
		order.setTotalPrice(totalPrice);
		order.setDetails(ods);
		orderModelService.updateOrder(order);
		response.getWriter().write("success");
		
	}
	
	
	
	
	
}

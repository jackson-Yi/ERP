package cn.tx.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import cn.tx.model.Store;
import cn.tx.model.StoreDetail;
import cn.tx.query.OrderModelQuery;
import cn.tx.query.StoreQuery;
import cn.tx.service.OrderModelService;
import cn.tx.service.StoreService;
import cn.tx.utils.Page;

import com.opensymphony.xwork2.ActionContext;

public class StoreAction extends BaseAction {
	
	
	private StoreQuery query = new StoreQuery();
	
	private Integer productNum;
	
	private Integer productId;
	
	private Integer orderDetailId;
	
	
	


	public Integer getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Integer orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getProductNum() {
		return productNum;
	}

	public void setProductNum(Integer productNum) {
		this.productNum = productNum;
	}

	public OrderModelService getOrderModelService() {
		return orderModelService;
	}

	private OrderModelService orderModelService;
	
	public void setOrderModelService(OrderModelService orderModelService) {
		this.orderModelService = orderModelService;
	}
	
	public StoreQuery getQuery() {
		return query;
	}

	public void setQuery(StoreQuery query) {
		this.query = query;
	}

	private StoreService storeService;
	
	
	
	
	
	public StoreService getStoreService() {
		return storeService;
	}

	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}


	public String store_slist(){
		
		ActionContext context = ActionContext.getContext();
		
		Integer pageNo = query.getPageNo();
		if(pageNo == null ){
			query.setPageNo(1);
		}
		
		Page page = storeService.queryObjByCondition(query, exclude);
		
		context.put("page", page);
		
		
		return SUCCESS;
	}
	
	public String store_input(){
		return SUCCESS;
	}
	public String store_storeDetail(){
		Store store = storeService.getObj(query.getStoreId());
		Set<StoreDetail> details = store.getDetails();
		ActionContext context = ActionContext.getContext();
		context.put("details", details);
		return SUCCESS;
	}
	public String store_inStock(){
		List<Store> list = storeService.list();
		ActionContext context = ActionContext.getContext();
		context.put("sLsit", list);
		return SUCCESS;
	}
	
	
	public void ajax_store_inStock() throws IOException{
		
		try {
			storeService.updateInstock(query.getStoreId(), productId, productNum, orderDetailId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.getWriter().write("success");
		
	}
	
	
}

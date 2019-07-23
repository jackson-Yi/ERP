package cn.tx.controller;

import java.util.List;

import cn.tx.model.StoreDetail;
import cn.tx.query.StoreDetailQuery;
import cn.tx.service.StoreDetailService;
import cn.tx.utils.Page;

import com.opensymphony.xwork2.ActionContext;

public class StoreDetailAction extends BaseAction {

	private StoreDetailQuery query = new StoreDetailQuery();
	
	public StoreDetailQuery getQuery() {
		return query;
	}

	public void setQuery(StoreDetailQuery query) {
		this.query = query;
	}

	private StoreDetailService storeDetailService;
	
	public StoreDetailService getStoreDetailService() {
		return storeDetailService;
	}

	public void setStoreDetailService(StoreDetailService storeDetailService) {
		this.storeDetailService = storeDetailService;
	}

	public String storeDetail_list(){
		
		ActionContext context = ActionContext.getContext();
		
		Integer pageNo = query.getPageNo();
		if(pageNo == null ){
			query.setPageNo(1);
		}
		
		Page page = storeDetailService.queryObjByCondition(query, exclude);
		
		context.put("page", page);
		return SUCCESS;
	}
	
	public String storeDetail_input(){
		return SUCCESS;
	}
	
	
}

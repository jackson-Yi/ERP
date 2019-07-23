package cn.tx.controller;

import java.util.List;

import cn.tx.model.Dep;
import cn.tx.query.DepQuery;
import cn.tx.service.DepService;
import cn.tx.utils.Page;

import com.opensymphony.xwork2.ActionContext;

public class DepAction extends BaseAction {
	
	/**
	 * 接收查询对象
	 */
	private DepQuery query = new DepQuery();
	
	
	
	
	
	
	public DepQuery getQuery() {
		return query;
	}

	public void setQuery(DepQuery query) {
		this.query = query;
	}

	private DepService depService;
	
	
	
	
	
	public DepService getDepService() {
		return depService;
	}

	public void setDepService(DepService depService) {
		this.depService = depService;
	}


	public String dep_list(){
		//查询部门信息
		List<Dep> list = depService.list();
		ActionContext context = ActionContext.getContext();
		context.put("dList", list);
		
		Integer pageNo = query.getPageNo();
		if(pageNo == null ){
			query.setPageNo(1);
		}
		//查询数据
		Page page = depService.queryObjByCondition(query, exclude);
		
		context.put("page", page);
		
		
		return SUCCESS;
	}
	
	public String dep_input(){
		return SUCCESS;
	}
	
	
}

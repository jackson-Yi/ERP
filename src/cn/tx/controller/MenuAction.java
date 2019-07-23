package cn.tx.controller;

import java.util.List;

import cn.tx.model.Menu;
import cn.tx.query.MenuQuery;
import cn.tx.service.MenuService;
import cn.tx.utils.Page;

import com.opensymphony.xwork2.ActionContext;

public class MenuAction extends BaseAction {

	private MenuQuery query = new MenuQuery();
	
	public MenuQuery getQuery() {
		return query;
	}

	public void setQuery(MenuQuery query) {
		this.query = query;
	}

	private MenuService menuService;
	
	public MenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	public String menu_list(){
		
		ActionContext context = ActionContext.getContext();
		
		Integer pageNo = query.getPageNo();
		if(pageNo == null ){
			query.setPageNo(1);
		}
		
		Page page = menuService.queryObjByCondition(query, exclude);
		
		context.put("page", page);
		return SUCCESS;
	}
	
	public String menu_input(){
		return SUCCESS;
	}
	
	
}

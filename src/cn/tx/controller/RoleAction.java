package cn.tx.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import cn.tx.model.Menu;
import cn.tx.model.Role;
import cn.tx.query.RoleQuery;
import cn.tx.service.MenuService;
import cn.tx.service.RoleService;
import cn.tx.utils.Page;

import com.opensymphony.xwork2.ActionContext;

public class RoleAction extends BaseAction {
	
	/**
	 * 接收查询对象
	 */
	private RoleQuery query = new RoleQuery();
	
	private String permIds;
	
	private MenuService menuService;
	
	

	public String getPermIds() {
		return permIds;
	}

	public void setPermIds(String permIds) {
		this.permIds = permIds;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	public RoleQuery getQuery() {
		return query;
	}

	public void setQuery(RoleQuery query) {
		this.query = query;
	}

	private RoleService roleService;
	
	
	
	
	
	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService RoleService) {
		this.roleService = RoleService;
	}


	public String role_list(){
		
		ActionContext context = ActionContext.getContext();
		
		Integer pageNo = query.getPageNo();
		if(pageNo == null ){
			query.setPageNo(1);
		}
		//查询数据
		Page page = roleService.queryObjByCondition(query, exclude);
		
		context.put("page", page);
		
		
		return SUCCESS;
	}
	
	public String Role_input(){
		return SUCCESS;
	}
	
	public String role_listperm(){
		//根据roleId查询role对象
		Role role1 = roleService.getObj(query.getRoleId());
		Set<Menu> menus = role1.getMenus();
		
		//获得系统菜单
		Menu rootMenu = menuService.getObj(1);
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		
		createTreeData(rootMenu, list, menus);
		JSONArray ja = JSONArray.fromObject(list);
		ActionContext context = ActionContext.getContext();
		context.put("zNodes", ja);
		return SUCCESS;
	}
	
	public void createTreeData(Menu menu, List<Map<String, Object>>list){
		Map<String,Object> map = new HashMap<String, Object>();
		if(menu != null){
			Integer id = menu.getMenuId();
			Integer pId = menu.getParentMenuId();
			String name = menu.getName();
			if(id.intValue() != 1){
				map.put("id", id);
				map.put("pId", pId);
				map.put("name", name);
				list.add(map);
			}
			/*if(id.intValue() == 1){
				map.put("open", true);
			}*/
			Set<Menu> menus = menu.getMenus();
			if(menus != null && menus.size() > 0){
				for(Menu m: menus){
					createTreeData(m, list);
				}
			}
		}
	}
	
	public void createTreeData(Menu menu, List<Map<String, Object>> list, Set<Menu> roleMenus){
		Map<String,Object> map = new HashMap<String, Object>();
		if(menu != null){
			Integer id = menu.getMenuId();
			Integer pId = menu.getParentMenuId();
			String name = menu.getName();
			if(id.intValue() != 1){
				map.put("id", id);
				map.put("pId", pId);
				map.put("name", name);
				for(Menu m : roleMenus){
					if(m.getMenuId().intValue() == menu.getMenuId().intValue()){
						map.put("checked", true);
						map.put("open", true);
						break;
					}
				}
				
				list.add(map);
			}
			Set<Menu> menus = menu.getMenus();
			if(menus != null && menus.size() > 0){
				for(Menu m: menus){
					createTreeData(m, list,roleMenus);
				}
			}
		}
	}
	
	public void ajax_role_grantPerm() throws IOException{
		roleService.updateGrantPerm(query.getRoleId(), permIds);
		response.getWriter().write("success");
	}
	
	
}

package cn.tx.service.impl;

import cn.tx.dao.MenuDao;
import cn.tx.model.Menu;
import cn.tx.query.MenuQuery;
import cn.tx.service.MenuService;

public class MenuServiceImpl extends BaseServiceImpl<Menu, MenuQuery> implements MenuService {

	
	private MenuDao menuDao;
	
	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
		this.baseDao = menuDao;
	}
	
	

}

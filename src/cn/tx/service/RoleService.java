package cn.tx.service;

import cn.tx.model.Role;
import cn.tx.query.RoleQuery;

public interface RoleService extends BaseService<Role, RoleQuery> {
	
	public void updateGrantPerm(Integer roleId, String permIds);

}

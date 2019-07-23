package cn.tx.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import cn.tx.dao.EmpDao;
import cn.tx.dao.RoleDao;
import cn.tx.model.Emp;
import cn.tx.model.Role;
import cn.tx.query.EmpQuery;
import cn.tx.service.EmpService;

public class EmpServiceImpl extends BaseServiceImpl<Emp, EmpQuery> implements EmpService {

	
	private EmpDao empDao;
	
	private RoleDao roleDao;
	
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	
	public void setEmpDao(EmpDao empDao) {
		this.empDao = empDao;
		this.baseDao = empDao;
	}

	@Override
	public Emp getEmpByUname(String username) {
		return empDao.getEmpByUname(username);
	}

	@Override
	public void updateEmp(Emp emp) {
		Emp emp1 = empDao.getObj(emp.getEmpId());
		emp.setPassword(emp1.getPassword());
		try {
			//把emp中的所有属性拷贝到emp1中
			BeanUtils.copyProperties(emp1, emp);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		empDao.update(emp1);
	}

	@Override
	public List<Role> getStateRoles(Integer empId) {
		//查询所有角色
		List<Role> list = roleDao.list();
		Emp emp = empDao.getObj(empId);
		Set<Role> roles = emp.getRoles();
		for(Role r : list){
			for(Role er : roles){
				if(r.getRoleId().intValue() == er.getRoleId().intValue()){
					r.setSelect("yes");
				}
			}
		}
		return list;
	}

	@Override
	public void updateEmpRole(Integer empId, String roleIds) {
		//获得用户
		Emp emp = empDao.getObj(empId);
		Set<Role> roles = emp.getRoles();
		//清除原有数据
		roles.clear();
		if(StringUtils.isNotBlank(roleIds)){
			String[] ids = roleIds.split(",");
			for(String roleId : ids){
				//根据id获得要分配role对象
				Role role = roleDao.getObj(new Integer(roleId));
				//添加新的角色
				roles.add(role);
			}
		}
	}

	@Override
	public Emp getEmpByUnameAndPWord(String username, String password) {
		return empDao.getEmpByUnameAndPWord(username, password);
	}
	
	

}

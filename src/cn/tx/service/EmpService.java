package cn.tx.service;

import java.util.List;

import cn.tx.model.Emp;
import cn.tx.model.Role;
import cn.tx.query.EmpQuery;

	

public interface EmpService extends BaseService<Emp, EmpQuery> {

	public Emp getEmpByUname(String username);
	
	public void updateEmp(Emp emp);
	
	public List<Role> getStateRoles(Integer empId);
	
	public void updateEmpRole(Integer empId, String roleIds);
	
	public Emp getEmpByUnameAndPWord(String username, String password);
}
	
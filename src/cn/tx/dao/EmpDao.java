package cn.tx.dao;

import cn.tx.model.Emp;
import cn.tx.query.EmpQuery;

public interface EmpDao extends BaseDao<Emp, EmpQuery> {
	
	public Emp getEmpByUname(String username);
	
	public Emp getEmpByUnameAndPWord(String username, String password);
}

package cn.tx.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import cn.tx.dao.EmpDao;
import cn.tx.model.Emp;
import cn.tx.query.EmpQuery;

public class EmpDaoImpl extends BaseDaoImpl<Emp, EmpQuery> implements EmpDao {

	@Override
	public String createHql(EmpQuery equery) {
		String hql = "from Emp t where 1=1 ";
		hql = hql + createHqlCondition(equery) + "order by t.empId desc";
		return hql ;
	}

	@Override
	public String createHqlCount(EmpQuery q) {
		String hql = "select count(empId) from Emp t where 1=1";
		hql = hql + createHqlCondition(q);		
				
		
		return hql;
	}

	@Override
	public String createHqlCondition(EmpQuery equery) {
		String hql = "";
		if(StringUtils.isNotBlank(equery.getUsername())){
			hql = hql + " and t.username like :username";
		}
		if(StringUtils.isNotBlank(equery.getName())){
			hql = hql + " and t.name like :name";
		}
		if(StringUtils.isNotBlank(equery.getTel())){
			hql = hql + " and t.tel like :tel";
		}
		if(equery.getGender() != null){
			hql = hql + " and t.gender = :gender";
		}
		
		if(StringUtils.isNotBlank(equery.getEmail())){
			hql = hql + " and t.email like :email";
		}
		
		if(equery.getStartBirth() != null){
			hql = hql + " and t.birthday >= :startBirth";
		}
		
		if(equery.getEndBirth() != null){
			hql = hql + " and t.birthday <= :endBirth";
		}
		
		if(equery.getDepId() != null){
			hql = hql + " and t.dep.depId = :depId";
		}
		return hql;
	}

	@Override
	public Emp getEmpByUname(String username) {
		Emp emp = null;
		String hql = "from Emp e where e.username = ?";
		List list = this.getHibernateTemplate().find(hql, username);
		if(list.size() > 0){
			emp = (Emp) list.get(0);
		}
		return emp;
	}

	@Override
	public Emp getEmpByUnameAndPWord(final String username, final String password) {
		final String hql = "from Emp e where e.username = :username and e.password = :password";
		Emp emp = this.getHibernateTemplate().execute(new HibernateCallback<Emp>() {

			@Override
			public Emp doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(hql);
				query.setParameter("username", username);
				query.setParameter("password", password);
				return (Emp) query.uniqueResult();
			}
		});
		return emp;
	}

	

	

	
	

}

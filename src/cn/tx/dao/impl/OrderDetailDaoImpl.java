package cn.tx.dao.impl;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import cn.tx.dao.OrderDetailDao;
import cn.tx.model.OrderDetail;
import cn.tx.query.OrderDetailQuery;

public class OrderDetailDaoImpl extends BaseDaoImpl<OrderDetail, OrderDetailQuery> implements OrderDetailDao {

	@Override
	public String createHql(OrderDetailQuery equery) {
		String hql = "from OrderDetail t where 1=1 ";
		
		return hql;
	}

	@Override
	public String createHqlCount(OrderDetailQuery q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createHqlCondition(OrderDetailQuery q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteDetailByOrderId(final Integer orderId) {
		final String hql = "delete from OrderDetail t where t.orderId = :orderId";
		this.getHibernateTemplate().execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query q = session.createQuery(hql);
				q.setParameter("orderId", orderId+"");
				try {
					q.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		});
	}
	

}

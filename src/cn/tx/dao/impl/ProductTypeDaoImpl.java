package cn.tx.dao.impl;

import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import cn.tx.dao.ProductTypeDao;
import cn.tx.model.ProductType;
import cn.tx.query.ProductTypeQuery;

public class ProductTypeDaoImpl extends BaseDaoImpl<ProductType, ProductTypeQuery> implements ProductTypeDao {

	@Override
	public String createHql(ProductTypeQuery equery) {
		String hql = "from ProductType t where 1=1 ";
		hql = hql + createHqlCondition(equery) + "order by t.productTypeId desc";
		return hql;
	}

	@Override
	public String createHqlCount(ProductTypeQuery q) {
		String hql = "select count(t.productTypeId) from ProductType t where 1=1 ";
		hql = hql + createHqlCondition(q);
		return hql;
	}

	@Override
	public String createHqlCondition(ProductTypeQuery q) {
		String hql = "";
		if(StringUtils.isNotBlank(q.getName())){
			hql = hql + " and t.name like :name";
		}
		if(q.getSupplierId() != null){
			hql = hql + " and t.supplier.supplierId = :supplierId";
		}
		return hql;
	}

	@Override
	public ProductType getProductTypeBySName(final ProductType pt) {
		final String hql = "from ProductType t where t.supplier.supplierId = :supplierId and t.name = :name";
		ProductType productType = this.getHibernateTemplate().execute(new HibernateCallback<ProductType>() {

			@Override
			public ProductType doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				query.setParameter("supplierId", pt.getSupplierId());
				query.setParameter("name", pt.getName());
				return (ProductType) query.uniqueResult();
			}
			
		});
		return productType;
	}
	

}

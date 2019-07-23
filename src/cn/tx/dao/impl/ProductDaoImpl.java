package cn.tx.dao.impl;

import org.apache.commons.lang.StringUtils;

import cn.tx.dao.ProductDao;
import cn.tx.model.Product;
import cn.tx.query.ProductQuery;

public class ProductDaoImpl extends BaseDaoImpl<Product, ProductQuery> implements ProductDao {

	@Override
	public String createHql(ProductQuery equery) {
		String hql = "from Product t where 1=1 ";
		hql = hql + createHqlCondition(equery) + " order by t.productId desc";
		return hql;
	}

	@Override
	public String createHqlCount(ProductQuery q) {
		String hql = "select count(t.productId) from Product t where 1=1";
		hql = hql + createHqlCondition(q);
		return hql;
	}

	@Override
	public String createHqlCondition(ProductQuery q) {
		String hql = "";
		if(q.getSupplierId() != null){
			hql = hql + " and t.productType.supplier.supplierId = :supplierId";
		}
		
		if(StringUtils.isNotBlank(q.getName())){
			hql = hql + " and t.name like :name";
		}
		
		if(StringUtils.isNotBlank(q.getProducer())){
			hql = hql + " and t.producer like :producer";
		}
		
		if(StringUtils.isNotBlank(q.getUnit())){
			hql = hql + " and t.unit like :unit";
		}
		
		if(q.getMinInPrice() != null){
			hql = hql + " and t.inPrice >= :minInPrice";
		}
		
		if(q.getMaxInPrice() != null){
			hql = hql + " and t.inPrice <= :maxInPrice";
		}
		
		if(q.getMinOutPrice() != null){
			hql = hql + " and t.outPrice >= :minOutPrice";
		}
		
		if(q.getMaxOutPrice() != null){
			hql = hql + " and t.outPrice <= :maxOutPrice";
		}

		return hql;
	}
	

}

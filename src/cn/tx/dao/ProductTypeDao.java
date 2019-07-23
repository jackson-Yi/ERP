package cn.tx.dao;

import cn.tx.model.ProductType;
import cn.tx.query.ProductTypeQuery;

public interface ProductTypeDao extends BaseDao<ProductType, ProductTypeQuery> {
	
	public ProductType getProductTypeBySName(ProductType pt);

}

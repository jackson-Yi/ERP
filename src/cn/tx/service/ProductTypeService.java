package cn.tx.service;

import cn.tx.model.ProductType;
import cn.tx.query.ProductTypeQuery;

public interface ProductTypeService extends BaseService<ProductType, ProductTypeQuery> {
	
	public ProductType getProductTypeBySName(ProductType pt);
	

}

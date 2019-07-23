package cn.tx.service.impl;

import cn.tx.dao.ProductTypeDao;
import cn.tx.model.ProductType;
import cn.tx.query.ProductTypeQuery;
import cn.tx.service.ProductTypeService;

public class ProductTypeServiceImpl extends BaseServiceImpl<ProductType, ProductTypeQuery> implements ProductTypeService {

	
	private ProductTypeDao productTypeDao;
	
	public void setProductTypeDao(ProductTypeDao productTypeDao) {
		this.productTypeDao = productTypeDao;
		this.baseDao = productTypeDao;
	}

	@Override
	public ProductType getProductTypeBySName(ProductType pt) {
		return productTypeDao.getProductTypeBySName(pt);
	}
	
	

}

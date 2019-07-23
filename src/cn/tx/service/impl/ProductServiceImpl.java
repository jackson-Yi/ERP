package cn.tx.service.impl;

import cn.tx.dao.ProductDao;
import cn.tx.model.Product;
import cn.tx.query.ProductQuery;
import cn.tx.service.ProductService;

public class ProductServiceImpl extends BaseServiceImpl<Product, ProductQuery> implements ProductService {

	
	private ProductDao productDao;
	
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
		this.baseDao = productDao;
	}
	
	

}

package cn.tx.controller;

import java.io.IOException;
import java.util.List;

import cn.tx.model.Product;
import cn.tx.model.Supplier;
import cn.tx.query.ProductQuery;
import cn.tx.service.ProductService;
import cn.tx.service.SupplierService;
import cn.tx.utils.JSONUtils;
import cn.tx.utils.Page;

import com.opensymphony.xwork2.ActionContext;

public class ProductAction extends BaseAction {

	private ProductQuery query = new ProductQuery();
	
	private Product product = new Product();
	
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public ProductQuery getQuery() {
		return query;
	}

	public void setQuery(ProductQuery query) {
		this.query = query;
	}

	private ProductService productService;
	
	private SupplierService supplierService;
	
	
	
	public SupplierService getSupplierService() {
		return supplierService;
	}

	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public String product_list(){
		
		ActionContext context = ActionContext.getContext();
		
		//查询所有的供应商
		List<Supplier> suppliers = supplierService.list();
		context.put("suppliers", suppliers);
		
		
		Integer pageNo = query.getPageNo();
		if(pageNo == null ){
			query.setPageNo(1);
		}
		
		Page page = productService.queryObjByCondition(query, exclude);
		
		context.put("page", page);
		return SUCCESS;
	}
	
	public String product_input(){
		ActionContext context = ActionContext.getContext();
		
		//查询所有的供应商
		List<Supplier> suppliers = supplierService.list();
		context.put("suppliers", suppliers);	
		return SUCCESS;
	}
	
	/*
	 * ajax实现
	 */
	public void ajax_product_add() throws IOException{
		productService.save(product);
		response.getWriter().write("success");
	}
	
	public void ajax_product_getProduct() throws IOException{
		Product pro = productService.getObj(query.getProductId());
		JSONUtils.printJSON(response, pro, new String[]{"productType"});
	}
}

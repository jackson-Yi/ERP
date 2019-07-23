package cn.tx.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import cn.tx.model.Product;
import cn.tx.model.ProductType;
import cn.tx.model.Supplier;
import cn.tx.query.ProductTypeQuery;
import cn.tx.service.ProductTypeService;
import cn.tx.service.SupplierService;
import cn.tx.utils.JSONUtils;
import cn.tx.utils.Page;

import com.opensymphony.xwork2.ActionContext;

public class ProductTypeAction extends BaseAction {
	
	
	private ProductTypeQuery query = new ProductTypeQuery();
	
	private ProductType productType = new ProductType();

	
	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public ProductTypeQuery getQuery() {
		return query;
	}

	public void setQuery(ProductTypeQuery query) {
		this.query = query;
	}

	private ProductTypeService productTypeService;
	
	private SupplierService supplierService;
	
	public SupplierService getSupplierService() {
		return supplierService;
	}

	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}

	public ProductTypeService getProductTypeService() {
		return productTypeService;
	}

	public void setProductTypeService(ProductTypeService productTypeService) {
		this.productTypeService = productTypeService;
	}

	public String productType_list(){
		
		ActionContext context = ActionContext.getContext();
		
		//查询所有的供应商
		List<Supplier> suppliers = supplierService.list();
		
		Integer pageNo = query.getPageNo();
		if(pageNo == null ){
			query.setPageNo(1);
		}
		
		Page page = productTypeService.queryObjByCondition(query, exclude);
		
		context.put("page", page);
		context.put("suppliers", suppliers);
		return SUCCESS;
	}
	
	public String productType_input(){
		ActionContext context = ActionContext.getContext();
		
		//查询所有的供应商
		List<Supplier> suppliers = supplierService.list();
		context.put("suppliers", suppliers);
		return SUCCESS;
	}
	
	/**
	 * ajax方法区
	 * @throws IOException 
	 */
	public void ajax_productType_add() throws IOException{
		productTypeService.save(productType);
		response.getWriter().write("success");;
	}
	
	public void ajax_productType_validUname() throws IOException{
		String result = "yes";
		ProductType pt = productTypeService.getProductTypeBySName(productType);
		if(pt != null){
			result = "no";
		}
		response.getWriter().write(result);
	}
	
	
	public void ajax_productType_getProduct() throws IOException{
		ProductType pt = productTypeService.getObj(query.getProductTypeId());
		Set<Product> products = pt.getProducts();
		JSONUtils.printJSONArray(response, products, new String[]{"productType"});
		
	}
	
	
	
}

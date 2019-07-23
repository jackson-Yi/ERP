package cn.tx.controller;

import java.util.List;
import java.util.Set;

import cn.tx.model.ProductType;
import cn.tx.model.Supplier;
import cn.tx.query.SupplierQuery;
import cn.tx.service.SupplierService;
import cn.tx.utils.JSONUtils;
import cn.tx.utils.Page;

import com.opensymphony.xwork2.ActionContext;

public class SupplierAction extends BaseAction {

	private SupplierQuery query = new SupplierQuery();
	
	public SupplierQuery getQuery() {
		return query;
	}

	public void setQuery(SupplierQuery query) {
		this.query = query;
	}

	private SupplierService supplierService;
	
	public SupplierService getSupplierService() {
		return supplierService;
	}

	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}

	public String supplier_list(){
		
		ActionContext context = ActionContext.getContext();
		
		Integer pageNo = query.getPageNo();
		if(pageNo == null ){
			query.setPageNo(1);
		}
		
		Page page = supplierService.queryObjByCondition(query, exclude);
		
		context.put("page", page);
		return SUCCESS;
	}
	
	public String supplier_input(){
		return SUCCESS;
	}
	
	/**
	 * 查询供应商
	 */
	public void ajax_supplier_getSupplier(){
		//根据Id查询供应商
		Supplier supplier = supplierService.getObj(query.getSupplierId());
		//获得所有供应商下的类别
		Set<ProductType> pts = supplier.getPts();
		JSONUtils.printJSONArray(response, pts, new String[]{"supplier", "products"});
	}
	
	
}

package cn.tx.model;

import java.util.Set;

/**
 * ProductType entity. @author MyEclipse Persistence Tools
 */

public class ProductType implements java.io.Serializable {

	// Fields

	private Integer productTypeId;
	private Integer supplierId;
	private String name;
	
	private Supplier supplier;

	private Set<Product> products;
	
	// Constructors

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	/** default constructor */
	public ProductType() {
	}

	/** full constructor */
	public ProductType(Integer supplierId, String name) {
		this.supplierId = supplierId;
		this.name = name;
	}

	// Property accessors

	public Integer getProductTypeId() {
		return this.productTypeId;
	}

	public void setProductTypeId(Integer productTypeId) {
		this.productTypeId = productTypeId;
	}

	public Integer getSupplierId() {
		return this.supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
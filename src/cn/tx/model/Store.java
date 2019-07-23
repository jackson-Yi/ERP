package cn.tx.model;

import java.util.Set;

/**
 * Store entity. @author MyEclipse Persistence Tools
 */

public class Store implements java.io.Serializable {

	// Fields

	private Integer storeId;
	private Integer stockman;
	private String name;
	private String address;
	
	private Emp storeAdmin;
	
	private Set<StoreDetail> details;
	
	

	// Constructors

	public Set<StoreDetail> getDetails() {
		return details;
	}

	public void setDetails(Set<StoreDetail> details) {
		this.details = details;
	}

	public Emp getStoreAdmin() {
		return storeAdmin;
	}

	public void setStoreAdmin(Emp storeAdmin) {
		this.storeAdmin = storeAdmin;
	}

	/** default constructor */
	public Store() {
	}

	/** full constructor */
	public Store(Integer stockman, String name, String address) {
		this.stockman = stockman;
		this.name = name;
		this.address = address;
	}

	// Property accessors

	public Integer getStoreId() {
		return this.storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getStockman() {
		return this.stockman;
	}

	public void setStockman(Integer stockman) {
		this.stockman = stockman;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
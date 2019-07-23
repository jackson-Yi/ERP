package cn.tx.query;

import cn.tx.model.Product;

public class ProductQuery extends Product{
	private Integer pageNo;
	
	private Integer startNum;
	
	private Integer supplierId;

	private Double minInPrice;

	private Double maxInPrice;
	
	public Double getMinInPrice() {
		return minInPrice;
	}

	public void setMinInPrice(Double minInPrice) {
		this.minInPrice = minInPrice;
	}

	public Double getMaxInPrice() {
		return maxInPrice;
	}

	public void setMaxInPrice(Double maxInPrice) {
		this.maxInPrice = maxInPrice;
	}

	public Double getMinOutPrice() {
		return minOutPrice;
	}

	public void setMinOutPrice(Double minOutPrice) {
		this.minOutPrice = minOutPrice;
	}

	public Double getMaxOutPrice() {
		return maxOutPrice;
	}

	public void setMaxOutPrice(Double maxOutPrice) {
		this.maxOutPrice = maxOutPrice;
	}

	private Double minOutPrice;
	
	private Double maxOutPrice;

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getStartNum() {
		return startNum;
	}

	public void setStartNum(Integer startNum) {
		this.startNum = startNum;
	}
	
	
	
	

}

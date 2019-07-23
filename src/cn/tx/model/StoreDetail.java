package cn.tx.model;

/**
 * StoreDetail entity. @author MyEclipse Persistence Tools
 */

public class StoreDetail implements java.io.Serializable {

	// Fields

	private Integer detailId;
	private Integer storeId;
	private Integer productId;
	private Integer num;
	
	private Product product;

	// Constructors

	
	
	/** default constructor */
	public StoreDetail() {
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	/** full constructor */
	public StoreDetail(Integer storeId, Integer productId, Integer num) {
		this.storeId = storeId;
		this.productId = productId;
		this.num = num;
	}

	// Property accessors

	public Integer getDetailId() {
		return this.detailId;
	}

	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}

	public Integer getStoreId() {
		return this.storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getProductId() {
		return this.productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

}
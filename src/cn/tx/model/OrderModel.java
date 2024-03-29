package cn.tx.model;

import java.util.Date;
import java.util.Set;

/**
 * OrderModel entity. @author MyEclipse Persistence Tools
 */

public class OrderModel implements java.io.Serializable {

	// Fields

	private Integer orderId;
	private String orderNum;
	private Integer creater;
	private Date createTime;
	private Integer checker;
	private Date checkTime;
	private Integer completer;
	private Date endTime;
	private Integer orderType;
	private Integer orderState;
	private Integer totalNum;
	private Double totalPrice;
	private Integer supplierId;
	
	/**
	 * 订单创建人
	 */
	private Emp orderCreater;
	
	private Emp orderChecker;
	
	
	
	private Supplier supplier;
	
	private Set<ConsoleLog> logs;
	
	private Emp orderCompleter;
	
	
	
	public Emp getOrderCompleter() {
		return orderCompleter;
	}

	public void setOrderCompleter(Emp orderCompleter) {
		this.orderCompleter = orderCompleter;
	}

	public Emp getOrderChecker() {
		return orderChecker;
	}

	public void setOrderChecker(Emp orderChecker) {
		this.orderChecker = orderChecker;
	}

	public Set<ConsoleLog> getLogs() {
		return logs;
	}

	public void setLogs(Set<ConsoleLog> logs) {
		this.logs = logs;
	}

	/**
	 * 订单明细
	 * @return
	 */
	private Set<OrderDetail> details;
	
	

	// Constructors

	public Set<OrderDetail> getDetails() {
		return details;
	}

	public void setDetails(Set<OrderDetail> details) {
		this.details = details;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Emp getOrderCreater() {
		return orderCreater;
	}

	public void setOrderCreater(Emp orderCreater) {
		this.orderCreater = orderCreater;
	}

	/** default constructor */
	public OrderModel() {
	}

	/** full constructor */
	public OrderModel(String orderNum, Integer creater, Date createTime,
			Integer checker, Date checkTime, Integer completer, Date endTime,
			Integer orderType, Integer orderState, Integer totalNum,
			Double totalPrice, Integer supplierId) {
		this.orderNum = orderNum;
		this.creater = creater;
		this.createTime = createTime;
		this.checker = checker;
		this.checkTime = checkTime;
		this.completer = completer;
		this.endTime = endTime;
		this.orderType = orderType;
		this.orderState = orderState;
		this.totalNum = totalNum;
		this.totalPrice = totalPrice;
		this.supplierId = supplierId;
	}

	// Property accessors

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrderNum() {
		return this.orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getCreater() {
		return this.creater;
	}

	public void setCreater(Integer creater) {
		this.creater = creater;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getChecker() {
		return this.checker;
	}

	public void setChecker(Integer checker) {
		this.checker = checker;
	}

	public Date getCheckTime() {
		return this.checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public Integer getCompleter() {
		return this.completer;
	}

	public void setCompleter(Integer completer) {
		this.completer = completer;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getOrderType() {
		return this.orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Integer getOrderState() {
		return this.orderState;
	}

	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}

	public Integer getTotalNum() {
		return this.totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Double getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getSupplierId() {
		return this.supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

}
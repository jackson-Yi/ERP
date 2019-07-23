package cn.tx.query;

import cn.tx.model.OrderDetail;

public class OrderDetailQuery extends OrderDetail{
	private Integer pageNo;
	
	private Integer startNum;
	
	

	

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

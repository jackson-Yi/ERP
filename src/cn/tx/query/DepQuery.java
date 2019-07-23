package cn.tx.query;

import cn.tx.model.Dep;

public class DepQuery extends Dep{
	
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

package cn.tx.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import cn.tx.utils.ERPConstants;

public class ERPOrderTypeTag extends TagSupport {
	
	
	
	//设置订单类型值
		private String orderType;
		
		
		

		public String getOrderType() {
			return orderType;
		}




		public void setOrderType(String orderType) {
			this.orderType = orderType;
		}
	
	




	@Override
	public int doStartTag() throws JspException {
		//获得JSPWriter对象向jsp页面写文本
		JspWriter out = pageContext.getOut();
		String text = "";
		switch(orderType){
			case ERPConstants.ORDER_TYPE_BUY:
				text = ERPConstants.ORDER_TYPE_BUY_TEXT;
				break;
			case ERPConstants.ORDER_TYPE_TRANS:
				text = ERPConstants.ORDER_TYPE_TRANS_TEXT;
				break;
			case ERPConstants.ORDER_TYPE_INSTOCK:
				text = ERPConstants.ORDER_TYPE_INSTOCK_TEXT;
				break;
			case ERPConstants.ORDER_TYPE_SALES:
				text = ERPConstants.ORDER_TYPE_SALES_TEXT;
				break;
		}
		try {
			out.write(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doStartTag();
	}
	
	
	
	
	

}

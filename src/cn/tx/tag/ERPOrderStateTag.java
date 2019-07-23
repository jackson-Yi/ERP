package cn.tx.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import cn.tx.utils.ERPConstants;

public class ERPOrderStateTag extends TagSupport {
	
	//设置订单类型值
		private String orderType;
		
		private String orderState;
		
		
		

		public String getOrderState() {
			return orderState;
		}




		public void setOrderState(String orderState) {
			this.orderState = orderState;
		}




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
				switch(orderState){
					case ERPConstants.ORDER_TYPE_BUY_AUDIT:
						text = ERPConstants.ORDER_TYPE_BUY_AUDIT_TEXT;
						break;
					case ERPConstants.ORDER_TYPE_BUY_AUDIT_REJECT:
						text = ERPConstants.ORDER_TYPE_BUY_AUDIT_REJECT_TEXT;
						break;
					case ERPConstants.ORDER_TYPE_BUY_AUDIT_PASS:
						text = ERPConstants.ORDER_TYPE_BUY_AUDIT_PASS_TEXT;
						break;
				}
				break;
			case ERPConstants.ORDER_TYPE_TRANS:
				
				switch(orderState){
					case ERPConstants.ORDER_TYPE_TRANS_BUY:
						text = ERPConstants.ORDER_TYPE_TRANS_BUY_TEXT;
						break;
					case ERPConstants.ORDER_TYPE_TRANS_ASSGIN:
						text = ERPConstants.ORDER_TYPE_TRANS_ASSGIN_TEXT;
						break;
					case ERPConstants.ORDER_TYPE_TRANS_BUYING:
						text = ERPConstants.ORDER_TYPE_TRANS_BUYING_TEXT;
						break;
				}
				break;
			case ERPConstants.ORDER_TYPE_INSTOCK:
				switch(orderState){
					case ERPConstants.ORDER_TYPE_INSTOCK_WAIT:
						text = ERPConstants.ORDER_TYPE_INSTOCK_WAIT_TEXT;
						break;
					case ERPConstants.ORDER_TYPE_INSTOCK_INING:
						text = ERPConstants.ORDER_TYPE_INSTOCK_INING_TEXT;
						break;
					case ERPConstants.ORDER_TYPE_INSTOCK_FINISH:
						text = ERPConstants.ORDER_TYPE_INSTOCK_FINISH_TEXT;
						break;
				}
				
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

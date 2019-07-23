
package cn.tx.interceptor;

import java.util.Map;

import cn.tx.controller.BaseAction;
import cn.tx.model.Emp;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * @author PJY
 *
 */
public class LoginInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation ai) throws Exception {
		String result = null;
		//获得Action的容器
		ActionContext context = ai.getInvocationContext();
		//获得session
		Map<String, Object> session = context.getSession();
		//获得session中的用户
		Emp emp = (Emp) session.get("user");
		//判断emp是否为空
		if(emp != null){
			//让拦截器继续往下运行
			result = ai.invoke();
		}else{
			result = BaseAction.LOGIN;
		}
		return result;
	}
}

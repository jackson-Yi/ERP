package cn.tx.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {
	
	HttpServletRequest request;
	
	HttpServletResponse response;
	
	HttpSession session;
	
	ServletContext application;
	
	List<String> exclude = new ArrayList<String>();
	
	String LIST = "list";

	String MAIN = "main";
	
	public BaseAction() {
		exclude.add("pageNo");
		exclude.add("startNum");
		
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		session = request.getSession();
		application = ServletActionContext.getServletContext();
		
	}
	

}

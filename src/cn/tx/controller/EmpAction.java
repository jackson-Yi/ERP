package cn.tx.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;

import cn.tx.model.Dep;
import cn.tx.model.Emp;
import cn.tx.model.Menu;
import cn.tx.model.Role;
import cn.tx.query.EmpQuery;
import cn.tx.service.DepService;
import cn.tx.service.EmpService;
import cn.tx.utils.MD5Utils;
import cn.tx.utils.Page;

import com.opensymphony.xwork2.ActionContext;

public class EmpAction extends BaseAction {
	
	/**
	 * 接收查询对象
	 */
	private EmpQuery query = new EmpQuery();
	
	private Emp emp = new Emp();
	
	/**
	 * 用于接收验证码
	 */
	private String captcha;
	
	private String roleIds;
	
	
	
	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public Emp getEmp() {
		return emp;
	}

	public void setEmp(Emp emp) {
		this.emp = emp;
	}

	public EmpQuery getQuery() {
		return query;
	}

	public void setQuery(EmpQuery query) {
		this.query = query;
	}

	private EmpService empService;
	
	
	private DepService depService;
	
	
	
	
	
	public DepService getDepService() {
		return depService;
	}

	public void setDepService(DepService depService) {
		this.depService = depService;
	}

	public EmpService getEmpService() {
		return empService;
	}

	public void setEmpService(EmpService empService) {
		this.empService = empService;
	}

	public String emp_list(){
		//查询部门信息
		List<Dep> list = depService.list();
		ActionContext context = ActionContext.getContext();
		context.put("dList", list);
		
		Integer pageNo = query.getPageNo();
		if(pageNo == null ){
			query.setPageNo(1);
		}
		//查询数据
		Page page = empService.queryObjByCondition(query, exclude);
		
		context.put("page", page);
		
		
		return SUCCESS;
	}
	
	public String emp_input(){
		List<Dep> list = depService.list();
		ActionContext context = ActionContext.getContext();
		context.put("dList", list);
		return SUCCESS;
	}
	/**
	 * 根据empId来查询员工
	 * @return
	 */
	public String emp_update(){
		List<Dep> list = depService.list();
		ActionContext context = ActionContext.getContext();
		context.put("dList", list);
		emp = empService.getObj(emp.getEmpId());
		return SUCCESS;
	}
	
	/**
	 * 删除员工
	 * @return
	 */
	public String emp_delete(){
		empService.delete(emp.getEmpId());
		return LIST;
	}
	
	
	/**
	 * 查询所有角色和当前员工的所有角色
	 * @return
	 */
	public String emp_listpop(){
		List<Role> roles = empService.getStateRoles(emp.getEmpId());
		ActionContext context = ActionContext.getContext();
		context.put("roles", roles);
		return SUCCESS;
	}
	
	public String emp_toLogin(){
		
		return SUCCESS;
	}
	
	/**
	 * 登录
	 * @return
	 */
	public String emp_login(){
		ActionContext context = ActionContext.getContext();
		//获得正确的验证码
		String rightCap = (String) session.getAttribute("piccode");
		if(!StringUtils.equalsIgnoreCase(rightCap, captcha)){
			context.put("tip", "caperror");
			return LOGIN;
		}
		//我们要给明文的密码做加密
		String newPass = MD5Utils.md5(emp.getPassword());
		Emp emp1 = empService.getEmpByUnameAndPWord(emp.getUsername(), newPass);
		if(emp1 == null){
			context.put("tip", "userpasserror");
			return LOGIN;
		}
		//获得struts的session
		Map<String, Object> session2 = context.getSession();
		//把用户的信息放入session中
		session2.put("user", emp1);
		
		Set<Role> roles = emp1.getRoles();
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		for(Role r : roles){
			Set<Menu> menus = r.getMenus();
			createTreeData(list, menus);
		}
		JSONArray ja = JSONArray.fromObject(list);
		context.getSession().put("zNodes", ja);
		
		return MAIN;
	}
	
	public String emp_logout(){
		ActionContext context = ActionContext.getContext();
		
		Map<String, Object> session2 = context.getSession();
		session2.remove("user");
		return MAIN;
	}
	
	
	public void createTreeData(List<Map<String, Object>> list, Set<Menu> menus){
		for(Menu m : menus){
			Map<String,Object> map = new HashMap<String, Object>();
			Integer id = m.getMenuId();
			Integer pId = m.getParentMenuId();
			String name = m.getName();
			map.put("id", id);
			map.put("pId", pId);
			map.put("name", name);
			map.put("url", m.getUrl());
			map.put("target", "main");
			list.add(map);
		}
	}
	
	
	
	/**
	 * 
	 * @return
	 * @throws IOException 
	 */
	public void ajax_emp_add() throws IOException{
		String password = emp.getPassword();
		 password = MD5Utils.md5(password);
		 emp.setPassword(password);
		empService.save(emp);
		response.getWriter().write("success");
	}
	/**
	 * 分配角色
	 * @throws IOException
	 */
	public void ajax_emp_grantrole() throws IOException{
		empService.updateEmpRole(emp.getEmpId(), roleIds);
		response.getWriter().write("success");
	}
	
	
	/**
	 * 修改用户
	 * @throws IOException
	 */
	public void ajax_emp_update() throws IOException{
	
		empService.updateEmp(emp);
		response.getWriter().write("success");
	}
	
	/**
	 * 校验用户名重复
	 * @throws IOException 
	 */
	public void ajax_emp_validUname() throws IOException{
		String result = "yes";
		Emp emp2 = empService.getEmpByUname(emp.getUsername());
		if(emp2 != null){
			result = "no";
		}
		response.getWriter().write(result);
	}
	
	
	/**
	 * 生成验证码的图片
	 * @throws Exception
	 */
	public void ajax_emp_getImage() throws Exception{
		 System.out.println("#######################生成数字和字母的验证码#######################");  
	        BufferedImage img = new BufferedImage(68, 22,  
	  
	        BufferedImage.TYPE_INT_RGB);  
	  
	        // 得到该图片的绘图对象    
	  
	        Graphics g = img.getGraphics();  
	  
	        Random r = new Random();  
	  
	        Color c = new Color(200, 150, 255);  
	  
	        g.setColor(c);  
	  
	        // 填充整个图片的颜色    
	  
	        g.fillRect(0, 0, 68, 22);  
	  
	        // 向图片中输出数字和字母    
	  
	        StringBuffer sb = new StringBuffer();  
	        
	  
	        char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();  
	  
	        int index, len = ch.length;  
	  
	        for (int i = 0; i < 4; i++) {  
	  
	            index = r.nextInt(len);  
	  
	            g.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt  
	  
	            (255)));  
	  
	            g.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22));  
	            // 输出的  字体和大小                      
	  
	            g.drawString("" + ch[index], (i * 15) + 3, 18);  
	            //写什么数字，在图片 的什么位置画    
	  
	            sb.append(ch[index]);  
	  
	        }  
	        //把验证码的值放入session中
	        request.getSession().setAttribute("piccode", sb.toString());  
	        //把验证码的图片写回浏览器
	        ImageIO.write(img, "JPG", response.getOutputStream());  
	}
	
	
	
	
}

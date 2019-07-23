package cn.tx.model;

import java.util.Date;
import java.util.Set;

/**
 * Emp entity. @author MyEclipse Persistence Tools
 */

public class Emp implements java.io.Serializable {

	// Fields

	private Integer empId;
	private Integer depId;
	private String name;
	private String username;
	private String email;
	private String tel;
	private Integer gender;
	private String address;
	private Date birthday;
	private String password;
	
	/**
	 * 从员工角度来看，员工和部门是多对一的关系
	 */
	private Dep dep;
	
	/**
	 * 一个用户有多个角色
	 */
	private Set<Role> roles; 

	// Constructors

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Dep getDep() {
		return dep;
	}

	public void setDep(Dep dep) {
		this.dep = dep;
	}

	/** default constructor */
	public Emp() {
	}

	/** full constructor */
	public Emp(Integer depId, String name, String username, String email,
			String tel, Integer gender, String address, Date birthday,
			String password) {
		this.depId = depId;
		this.name = name;
		this.username = username;
		this.email = email;
		this.tel = tel;
		this.gender = gender;
		this.address = address;
		this.birthday = birthday;
		this.password = password;
	}

	// Property accessors

	public Integer getEmpId() {
		return this.empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public Integer getDepId() {
		return this.depId;
	}

	public void setDepId(Integer depId) {
		this.depId = depId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Integer getGender() {
		return this.gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
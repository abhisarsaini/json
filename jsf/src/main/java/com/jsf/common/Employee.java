package com.jsf.common;
 
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.io.Serializable;
 
@ManagedBean
@SessionScoped
public class Employee implements Serializable {
 

	private static final long serialVersionUID = 1L;
 
	private String name;
	private int id;
	private String desig;
	private long Salary;
	
	public Employee(){
		
	}
 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesig() {
		return desig;
	}

	public void setDesig(String desig) {
		this.desig = desig;
	}

	public long getSalary() {
		return Salary;
	}

	public void setSalary(long salary) {
		Salary = salary;
	}
}
package com.jsf.common;

import java.io.Serializable;

import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.codehaus.jackson.annotate.JsonTypeInfo;

@PersistenceCapable(detachable = "true", table = "employee_records")
@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="@class")
public class Emp implements Serializable{
	
	/**
	 * 
	 */
	@NotPersistent
	private static final long serialVersionUID = 1L;
	@Persistent(primaryKey = "true",column = "Employee_Id")
	private int id;
	@Persistent(column= "Name")
	private String name;
	@Persistent(column= "Designation")
	private String desi;
	@Persistent(column= "Salary")
	private String salary;//TODO:double
	
    
	public Emp() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Emp(String name, int id, String desi, String salary) {
		super();
		this.name = name;
		this.id = id;
		this.desi = desi;
		this.salary = salary;
		
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
	
	public String getDesi() {
		return desi;
	}
 
	public void setDesi(String desi) {
		this.desi = desi;
	}
    
	public String getSalary() {
		return salary;
	}
 
	public void setSalary(String salary) {
		this.salary = salary;
	}
	
 
	@Override
	public String toString() {
		return "Id: " + id + " ,Name: " + name + " ,Deisgnation: "+ desi +" ,Salary: "+ salary +"\n";
	}
 
}
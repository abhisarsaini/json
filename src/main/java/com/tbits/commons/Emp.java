package com.tbits.commons;

import java.io.Serializable;


public class Emp implements Serializable{
	private String name;
	private String id; //TODO:long
	private String desi;
	private String salary;//TODO:double
	
    
	public Emp() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Emp(String name, String id, String desi, String salary) {
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
 
	public String getId() {
		return id;
	}
 
	public void setId(String id) {
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
		return "Id: " + id + ", ,Name: " + name + " ,Deisgnation: "+ desi +" ,Salary: "+ salary +"\n";
	}
 
}
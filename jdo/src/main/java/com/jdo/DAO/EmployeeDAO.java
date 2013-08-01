package com.jdo.DAO;

import javax.jdo.*;
import java.util.*;

import com.jdo.entities.*;


public class EmployeeDAO {

	static PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	static PersistenceManager pm = pmf.getPersistenceManager();
	
	public static void createEmployee(Employee emp)
	{
		Transaction tx=pm.currentTransaction();
		try
		{
		    tx.begin();
		    pm.makePersistent(emp);
		    tx.commit();
		    
		}
		finally
		{
			if (tx.isActive())
		    {
		        tx.rollback();
		    }
		    pm.close();
		}
		
	}
	
	public static ArrayList<Employee> readEmployee(Employee emp)
	{
		Transaction tx = pm.currentTransaction();
		ArrayList<Employee> t1 = new ArrayList();
		try
		{
			tx.begin();
			 Query q = pm.newQuery(Employee.class,"employeeId== " + emp.getEmployeeId());
			 System.out.println(emp.getEmployeeId());
			 List<Employee> e1 = (List<Employee>)q.execute();
			 Iterator<Employee> iter = e1.iterator();
			  while (iter.hasNext())
			    {
			        Employee e = iter.next();
			        System.out.println(e.toString());
			    }
			tx.commit();
		}
		finally
		{
			 if (tx.isActive())
			    {
			        tx.rollback();
			    }

			    pm.close();
		}
		return t1;
	}
	public static void deleteEmployee(Employee emp)
	{
		Transaction tx = pm.currentTransaction();
		try
		{
		    tx.begin();
		    Query q = pm.newQuery(Employee.class,"employeeId== " + emp.getEmployeeId() );
		    List<Employee> e1 = (List<Employee>) q.execute();
		    pm.deletePersistentAll(e1);
		    
		    tx.commit();
		}
		finally
		{
		    if (tx.isActive())
		    {
		        tx.rollback();
		    }

		    pm.close();
		}
	}
	public static void updateEmployee(Employee emp)
	{
		Transaction tx = pm.currentTransaction();
		try
		{
		    tx.begin();
		    Query q = pm.newQuery(Employee.class," employeeId== " + emp.getEmployeeId());
		    List<Employee> e1 = (List<Employee>) q.execute();
		    for(Employee e2:e1)
		    {
		    	e2.setName(emp.getName());
		    	e2.setDesignation(emp.getDesignation());
		    	e2.setSalary(emp.getSalary());
		    	System.out.println("h");
		    }
		    
		    tx.commit();
		}
		finally
		{
		    if (tx.isActive())
		    {
		        tx.rollback();
		    }

		    pm.close();
		}
	}
	
}

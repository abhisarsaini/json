package com.jsf.service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.jsf.common.Emp;




@Path("/json/")
public class JSONService {
	
	private static int pos=0;
	
	static PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	 
	@GET
	@Path("/read")	
	@Produces(MediaType.APPLICATION_JSON)
	
	
	public Response getnameJSON(@QueryParam("uid") int uid) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Query q= pm.newQuery(Emp.class, "id== "+uid);
		q.setUnique(true);
		Emp temp= (Emp) pm.detachCopy(q.execute());
		String result;
		if (temp==null)
		result="No such record found....";
		else
		result= "Output from server:"+temp;
		pm.close();
		return Response.status(201).entity(result).build();
 
	}
 
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTrackInJSON(Emp temp) {
		
		String result;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
		    tx.begin();
		    pm.detachCopy(pm.makePersistent(temp));
		    tx.commit();
		    result= "Saved....";
		}
		
		finally
		{
		    if (tx.isActive())
		    {
		        tx.rollback();
		        result= "Not Saved....";
		    }
		    pm.close();
		    
		}
		 
    	pos++;
		return Response.status(201).entity(result).build();
	}
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateTrackInJSON(@QueryParam("uid") int uid, Emp temp) {
 
		PersistenceManager pm= pmf.getPersistenceManager();
		Transaction tx= pm.currentTransaction();
		try
		{
			tx.begin();
			Query q= pm.newQuery(Emp.class,"id=="+uid);
			q.setUnique(true);
			Emp temp2= (Emp) pm.detachCopy(q.execute());
			if(temp2==null)
			{
				String result= "No record with this ID found. Nothing updated......";
				
				return Response.status(201).entity(result).build();		
			}
			temp2.setName(temp.getName());
			temp2.setDesi(temp.getDesi());
			temp2.setSalary(temp.getSalary());
			pm.makePersistent(temp2);
			tx.commit();
		}
		finally
		{
			if(tx.isActive())
			{
				tx.rollback();
			}
				pm.close();
		}
		String result = "Employee updated.....";
		return Response.status(201).entity(result).build();
 
	}
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListOfAllEmployees(){
    	PersistenceManager pm = pmf.getPersistenceManager();
    	Transaction tx = pm.currentTransaction();
    	
    	try {
    
	    	    tx.begin();
	    	    Query q = pm.newQuery(Emp.class);
	    
	    	    @SuppressWarnings("unchecked")
				Collection<Emp> collection = (Collection<Emp>)q.execute();
	    	    List<Emp> ListOfEmployee = (List<Emp>)pm.detachCopyAll(collection);
	    	    tx.commit();
	    	    GenericEntity<List<Emp>> genericEntity = new GenericEntity<List<Emp>>( ListOfEmployee) {};
	    	    return Response.status(Response.Status.OK.getStatusCode()).entity(genericEntity).build();
    	    
    	} 
    	finally {
    	    if (tx.isActive()) {
    		tx.rollback();
    	    }
    	    pm.close();
    	}

 }
	@DELETE
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteEmp(@QueryParam("uid") int uid)
	{
		PersistenceManager pm= pmf.getPersistenceManager();
		Transaction tx= pm.currentTransaction();
		try
		{
			tx.begin();
			Query q= pm.newQuery(Emp.class,"id=="+uid);
			q.setUnique(true);
			Emp temp2= (Emp) pm.detachCopy(q.execute());
			if(temp2==null)
			{
				String result= "No record with this ID found. Nothing deleted......";
				
				return Response.status(201).entity(result).build();		
			}
			
			pm.deletePersistent(temp2);
			tx.commit();
		}
		finally
		{
			if(tx.isActive())
			{
				tx.rollback();
			}
				pm.close();
		}
		String result = "Employee deleted.....";
		return Response.status(201).entity(result).build();
 
	}
	
}

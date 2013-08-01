package com.tbits.service;


import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.tbits.commons.Emp;




@Path("/json/")
public class JSONService {
	
	private static int pos=0;
	private static Emp[] a= new Emp[100];
	
	@GET
	@Path("/read")	
	@Produces(MediaType.APPLICATION_JSON)
	
	public Response getnameJSON(@QueryParam("uid") int uid) {
		String result= "Output from server:"+a[--uid];
		return Response.status(201).entity(result).build();
 
	}
 
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTrackInJSON(Emp temp) {
		
//		a[pos]=new Emp();
		a[pos]=temp;		
		String result = "Employee saved : " + a[pos];
    	pos++;
		return Response.status(201).entity(result).build();
	}
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateTrackInJSON(@QueryParam("uid") int uid, Emp temp) {
 
		
//		int i= Integer.parseInt(uid);
     	a[--uid]=temp;	
		String result = "Employee updated : " + a[uid];
		return Response.status(201).entity(result).build();
 
	}
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListOfAllEmployees(){
		List<Emp> listOfEmployees = new ArrayList<Emp>();
		for(int i=0 ; i<pos ; i++) {
			listOfEmployees.add(a[i]);
		}
		
		GenericEntity<List<Emp>> genericEntity = new GenericEntity<List<Emp>>(listOfEmployees){
		};
		
		return Response.status(Response.Status.OK.getStatusCode()).entity(genericEntity).build();
	}
 
}

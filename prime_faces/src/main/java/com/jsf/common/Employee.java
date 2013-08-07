package com.jsf.common;
 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.jsf.common.Emp;


@ManagedBean
@SessionScoped
public class Employee implements Serializable {
 

	private static final long serialVersionUID = 1L;
 
	private String name;
	private int id;
	private String desig;
	private String salary;
	private String msg;
	private int uid;
	private int listsize;
	
	private String selection;
	private List<Emp> list= new ArrayList<Emp>();
	private List<Emp> view=new ArrayList<Emp>();
	
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

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}
	

	public String getSelection() {
		return selection;
	}

	public void setSelection(String selection) {
		this.selection = selection;
	}
	

	public List<Emp> getList() {
		return list;
	}

	public void setList(List<Emp> list) {
		this.list = list;
	}
	public int getListsize() {
		return listsize;
	}

	public void setListsize(int listsize) {
		this.listsize = listsize;
	}
	
	public List<Emp> getView() {
		return view;
	}

	public void setView(List<Emp> view) {
		this.view = view;
	}

	public void addEmp() {
		try {

			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(
					JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

			Client client = Client.create(clientConfig);
			WebResource webResource = client.resource("http://localhost:8080/JavaServerFaces/rest/json/add");

			Emp emp = new Emp(name, id, desig, salary);

			ClientResponse response = webResource.type("application/json").accept(new MediaType("application", "json")).post(ClientResponse.class, emp);

			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}
			msg=" "+response.getEntity(String.class);
			
			
			id=0;
			name=null;
			desig=null;
			uid=0;
			salary=null;
			
		} catch (Exception e) {
			setMsg("Exception:"+e);
			e.printStackTrace();
		}

	}
	public void viewEmp() {
		try {

			ClientConfig clientConfig = new DefaultClientConfig(); // For
																	// changing
																	// default
																	// config
			clientConfig.getFeatures().put(
					JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE); // Adding
																			// Plain
																			// Ordinary
																			// Java
																			// Object
																			// Mapping

			Client client = Client.create(clientConfig); // Creating client with
															// added features


			WebResource webResource = client.resource("http://localhost:8080/JavaServerFaces/rest/json/read?uid="+ uid);

			ClientResponse response = webResource.type("application/json").accept(new MediaType("application", "json")).get(ClientResponse.class);

			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
			}
			Emp temp=new Emp();
			
			temp=response.getEntity(Emp.class);
			if(temp==null)
			{
				msg="No such record found...";
			}
			else
			{
				msg="Record found: "+temp;
				id=temp.getId();
				name=temp.getName();
				salary=temp.getSalary();
				desig=temp.getDesi();
				if(view.isEmpty()==false)
				{
					view.clear();
				}
				view.add(temp);
				
			}
			


		} catch (Exception e) {
			msg="Exception : "+e;
			e.printStackTrace();
		}
	}
	public void updateEmp() {
		try {

			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

			Client client = Client.create(clientConfig);
			

			WebResource webResource = client.resource("http://localhost:8080/JavaServerFaces/rest/json/update?uid="+ uid);

			
			Emp emp = new Emp(name, uid, desig, salary);

			ClientResponse response = webResource.type("application/json").accept(new MediaType("application", "json")).put(ClientResponse.class, emp);

			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			msg = " "+response.getEntity(String.class);
			
			id=0;
			name=null;
			desig=null;
			salary=null;
			uid=0;

		} catch (Exception e) {
			
			msg="Exception : "+e;
			e.printStackTrace();
		}

	}
	
	public void deleteEmp()
	{
	try {
		Client client = Client.create(); 

		WebResource webResource = client.resource("http://localhost:8080/JavaServerFaces/rest/json/delete?uid="+ uid);

		ClientResponse response = webResource.type("application/json").accept(new MediaType("application", "json")).delete(ClientResponse.class);

		if (response.getStatus() != 201) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}

		msg = "Output from Server .... \n"+response.getEntity(String.class);

	} 
	 catch (Exception e) {
		msg="Exception : "+e;
		e.printStackTrace();
	}
	}
	public String listEmp() {
		try {

			ClientConfig clientConfig = new DefaultClientConfig(); // For
																	// changing
																	// default
																	// config
			clientConfig.getFeatures().put(
					JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE); // Adding
																			// Plain
																			// Ordinary
																			// Java
																			// Object
																			// Mapping

			Client client = Client.create(clientConfig); // Creating client with
															// added features

			WebResource webResource = client
					.resource("http://localhost:8080/JavaServerFaces/rest/json/list");

			ClientResponse response = webResource.type("application/json").get(
					ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
				
			}

			list = response.getEntity(new GenericType<List<Emp>>() {
			});
			if(list.isEmpty()==true)
			{
				msg="No records";
			}
			else
				msg= "Records fetched....";
			listsize= list.size();
			return "list";
			

		} catch (Exception e) {
			msg="Exception: "+e;
			e.printStackTrace();
			return "list";
		}
	}
	
	public String callview()
	{
		return "view";
	}
	
	public String redirect()
		{
		makenull();
		return "hello";
		
		
		}
	public void makenull()
	{
		msg=null;
		salary=null;
		id=0;
		name=null;
		desig=null;
		uid=0;
		selection=null;
		view.clear();
		list.clear();
	}
}
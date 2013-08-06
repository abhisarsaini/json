package com.jsf.common;
 
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;


@ManagedBean
@SessionScoped
public class Employee implements Serializable {
 

	private static final long serialVersionUID = 1L;
 
	private String name;
	private int id;
	private String desig;
	private String salary;
	public String msg;
	private int uid;
	
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
			msg="Output from Server .... \n"+response.getEntity(String.class);
			
			msg=null;
			id=0;
			name=null;
			desig=null;
			uid=0;
			
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

			msg = "Output from Server .... \n"+response.getEntity(String.class);
			
			
			id=0;
			name=null;
			desig=null;
			uid=0;


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

			msg = "Output from Server .... \n"+response.getEntity(String.class);
			
			id=0;
			name=null;
			desig=null;
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
	
	public String redirect()
		{
		msg=null;
		return "hello";
		}
}
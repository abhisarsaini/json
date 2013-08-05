package com.tbits.myClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.tbits.commons.Emp;

public class MyClient {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int flag = 1;
		while (flag == 1) {
			System.out.println("Menu: ");
			System.out.println("1.Add ");
			System.out.println("2.View ");
			System.out.println("3.Update ");
			System.out.println("4.List all records");
			System.out.println("5.Delete ");
			System.out.println("0.Exit ");
			System.out.println("Enter Choice: ");
			try {
				BufferedReader bufferRead = new BufferedReader(
						new InputStreamReader(System.in));
				String s = bufferRead.readLine();

				System.out.println("You chosed: " + s);
				if ("1".equals(s) == true) {
					addEmp();
				}
				if ("2".equals(s) == true) {
					viewEmp();
				}
				if ("3".equals(s) == true) {
					updateEmp();
				}
				if ("4".equals(s) == true) {
					getListOfEmployees();
				}
				if ("5".equals(s) == true) {
					deleteEmp();
				}

				if ("0".equals(s) == true) {
					flag = 0;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	private static void getListOfEmployees() {
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
					.resource("http://localhost:8080/jersey/rest/json/list");

			ClientResponse response = webResource.type("application/json").get(
					ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			System.out.println("Output from Server .... \n");
			List<Emp> output = response.getEntity(new GenericType<List<Emp>>() {
			});
			System.out.println("Total " + output.size() + " items received");
			int i=1;
			for (Emp emp : output) {
				System.out.println("Serial number: "+(i++)+" "+emp);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	private static void viewEmp() {
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

			BufferedReader bufferRead = new BufferedReader(
					new InputStreamReader(System.in));
			System.out
					.println("Enter the serial number of employee you want to view: ");
			String uid = bufferRead.readLine();

			WebResource webResource = client
					.resource("http://localhost:8080/jersey/rest/json/read?uid="
							+ uid);

			ClientResponse response = webResource.type("application/json").get(
					ClientResponse.class);

			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			String output = response.getEntity(String.class);

			System.out.println("Output from Server .... \n");
			System.out.println(output);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	private static void addEmp() {
		try {

			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(
					JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

			Client client = Client.create(clientConfig);

			WebResource webResource = client
					.resource("http://localhost:8080/jersey/rest/json/add");

			System.out.println("Enter Employee information ");

			BufferedReader bufferRead = new BufferedReader(
					new InputStreamReader(System.in));
			System.out.println("Id: ");
			String uid = bufferRead.readLine();
			System.out.println("Name: ");
			String name = bufferRead.readLine();
			System.out.println("Designation: ");
			String desi = bufferRead.readLine();
			System.out.println("Salary: ");
			String salary = bufferRead.readLine();
			int id= Integer.parseInt(uid);
			
			Emp emp = new Emp(name, id, desi, salary);

			ClientResponse response = webResource.type("application/json")
					.post(ClientResponse.class, emp);

			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			System.out.println("Output from Server .... \n");
			String output = response.getEntity(String.class);
			System.out.println(output);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	private static void updateEmp() {
		try {

			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(
					JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

			Client client = Client.create(clientConfig);
			BufferedReader bufferRead = new BufferedReader(
					new InputStreamReader(System.in));
			System.out
					.println("Enter the Employee Id of employee you want to update: ");
			String uid = bufferRead.readLine();

			WebResource webResource = client
					.resource("http://localhost:8080/jersey/rest/json/update?uid="
							+ uid);

			System.out.println("Enter Employee information ");

			System.out.println("Name: ");
			String name = bufferRead.readLine();
			System.out.println("Designation: ");
			String desi = bufferRead.readLine();
			System.out.println("Salary: ");
			String salary = bufferRead.readLine();
			
			int id= Integer.parseInt(uid);

			Emp emp = new Emp(name, id, desi, salary);

			ClientResponse response = webResource.type("application/json").put(
					ClientResponse.class, emp);

			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			System.out.println("Output from Server .... \n");
			String output = response.getEntity(String.class);
			System.out.println(output);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	private static void deleteEmp()
		{
		try {
			Client client = Client.create(); 
			BufferedReader bufferRead = new BufferedReader(
					new InputStreamReader(System.in));
			System.out
					.println("Enter the serial number of employee you want to delete: ");
			String uid = bufferRead.readLine();

			WebResource webResource = client
					.resource("http://localhost:8080/jersey/rest/json/delete?uid="
							+ uid);

			ClientResponse response = webResource.type("application/json").delete(
					ClientResponse.class);

			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			String output = response.getEntity(String.class);

			System.out.println("Output from Server .... \n");
			System.out.println(output);
		} 
		 catch (IOException e) {
			e.printStackTrace();
		}
		}			
}
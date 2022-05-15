package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Branch {
	//A common method to connect to the DB
	private Connection connect()
	 {
		 Connection con = null;
		 try
		 {
			 Class.forName("com.mysql.jdbc.Driver");
	
			 //Provide the correct details: DBServer/DBName, username, password
			 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "1998");
		 }
		 catch (Exception e)
		 {
			 e.printStackTrace();
		 }
	 return con;
	 }
	

	
	
	public String readBranch()
	{
		 String output = "";
		try
		 {
			 Connection con = connect();
			 if (con == null)
			 {
				 return "Error while connecting to the database for reading.";
			 }
				 // Prepare the html table to be displayed
				 output = "<table border=\"1\"><tr><th>Branch ID</th><th>Branch Name</th><th>Branch Address</th><th>District</th><th>Cover Areas</th><th>Update</th><th>Remove</th></tr>";
				 String query = "select * from branches";
				 Statement stmt = con.createStatement();
				 ResultSet rs = stmt.executeQuery(query);
				
				 
				 // iterate through the rows in the result set
			while (rs.next())
			{
				 String branchCode = Integer.toString(rs.getInt("branchCode"));
				 String branch_id = rs.getString("branch_id");
				 String branch_name = rs.getString("branch_name");
				 String branch_address = rs.getString("branch_address");
				 String district = rs.getString("district");
				 String cover_areas = rs.getString("cover_areas");
				 
				 
				 // Add into the html table
				 output += "<tr><td><input id='hidBranchIDUpdate'name='hidBranchIDUpdate'type='hidden' value='" + branchCode
						 + "'>" + branch_id + "</td>";
				 output += "<td>" + branch_name + "</td>";
				 output += "<td>" + branch_address + "</td>";
				 output += "<td>" + district + "</td>";
				 output += "<td>" + cover_areas + "</td>";
				 // buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' "
						 + "class='btnUpdate btn btn-secondary' data-branchCode='" + branchCode + "'></td>"
						 + "<td><input name='btnRemove' type='button' value='Remove' "
						 + "class='btnRemove btn btn-danger' data-branchCode='" + branchCode + "'></td></tr>";
			}
		 con.close();
		 // Complete the html table
		 output += "</table>";
		 }
		catch (Exception e)
		 {
			 output = "Error while reading the items.";
			 System.err.println(e.getMessage());
		 }
		return output;
	
	}	
	
	
	
	
	public String insertBranch(String id, String name, String address, String district, String cover_areas)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 // create a prepared statement
	 String query = " insert into branches(`branchCode`,`branch_id`,`branch_name`,`branch_address`,`district`,`cover_areas`)"+ " values (?, ?, ?, ?, ?,?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, id);
	 preparedStmt.setString(3, name);
	 preparedStmt.setString(4, address);
	 preparedStmt.setString(5, district);
	 preparedStmt.setString(6, cover_areas);
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 
	 String newBranch = readBranch();
	   output = "{\"status\":\"success\", \"data\": \"" + newBranch + "\"}";
	 }
	 catch (Exception e)
	 {
		 output = "{\"status\":\"error\", \"data\": \"Error while inserting the Branch.\"}";
		 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	
	
	
	
	
	
	public String updateBranch(String code,String id, String name, String address, String district, String cover_areas)
	{
		 String output = "";
		 try
		 {
			 Connection con = connect();
			 if (con == null)
			 {
				 return "Error while connecting to the database for updating."; 
			 }
			 // create a prepared statement
			 String query =  "UPDATE branches SET branch_id=?, branch_name=?, branch_address=?, district=?,cover_areas=? WHERE branchCode=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setString(1, id);
			 preparedStmt.setString(2, name);
			 preparedStmt.setString(3, address);
			 preparedStmt.setString(4, district);
			 preparedStmt.setString(5, cover_areas);
			 preparedStmt.setInt(6, Integer.parseInt(code));
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 String newBranch = readBranch();
			 output = "{\"status\":\"success\", \"data\": \"" + newBranch + "\"}";
		 	}
		 	catch (Exception e)
		 	{
		 		output = "{\"status\":\"error\", \"data\": \"Error while updating the branch.\"}";
		 		System.err.println(e.getMessage());
		 	}
		 return output;
		 }
	
	
	
	
	
	
		public String deleteBranch(String branchCode)
		 {
			 String output = "";
			 try
			 {
			 Connection con = connect();
			 if (con == null)
			 {
				 return "Error while connecting to the database for deleting."; 
			 }
				 // create a prepared statement
				 String query = "delete from branches where branchCode=?";
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 // binding values
				 preparedStmt.setInt(1, Integer.parseInt(branchCode));
				 // execute the statement
				 preparedStmt.execute();
				 con.close();
				 
				 String newBranch = readBranch();
				 output = "{\"status\":\"success\", \"data\": \"" + newBranch + "\"}";
			 }
			 catch (Exception e)
			 {
				 output = "{\"status\":\"error\", \"data\":\"Error while deleting the branch.\"}";
				 System.err.println(e.getMessage());
			 }
		 return output;
		 }
		

}

package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Branch;

@Path("/Branches")
public class BranchManagement {
	
	 Branch branchObj = new Branch();
	 

	 
	 @GET
	 @Path("/")
	 @Produces(MediaType.TEXT_HTML)
	 public String readBranch()
	  {
	  return branchObj.readBranch();
	 }
	 
	 @POST
	 @Path("/")
	 @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	 @Produces(MediaType.TEXT_PLAIN)
	 
	 public String insertBranch(@FormParam("branch_id") String branch_id,
	  @FormParam("branch_name") String branch_name,
	  @FormParam("branch_address") String branch_address,
	  @FormParam("district") String district,
	 @FormParam("cover_areas") String cover_areas)
	 {
	  String output = branchObj.insertBranch(branch_id, branch_name, branch_address, district, cover_areas);
	  return output;
	 }
	 
	 
	 
	 @PUT
	 @Path("/")
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.TEXT_PLAIN)
	 public String updateBranch(String branchData)
	 {
	 //Convert the input string to a JSON object
	  JsonObject branchObject = new JsonParser().parse(branchData).getAsJsonObject();
	 //Read the values from the JSON object
	  String branchCode = branchObject.get("branchCode").getAsString();
	  String branch_id = branchObject.get("branch_id").getAsString();
	  String branch_name = branchObject.get("branch_name").getAsString();
	  String branch_address = branchObject.get("branch_address").getAsString();
	  String district = branchObject.get("district").getAsString();
	  String cover_areas = branchObject.get("cover_areas").getAsString();
	  String output = branchObj.updateBranch(branchCode, branch_id, branch_name, branch_address, district,cover_areas);
	 return output;
	 }
	 
	 
	 
	 
	 @DELETE
	 @Path("/")
	 @Consumes(MediaType.APPLICATION_XML)
	 @Produces(MediaType.TEXT_PLAIN)
	 public String deleteBranch(String branchData)
	 {
	 //Convert the input string to an XML document
	  Document doc = Jsoup.parse(branchData, "", Parser.xmlParser());

	 //Read the value from the element <itemID>
	  String branchCode = doc.select("branchCode").text();
	  String output = branchObj.deleteBranch(branchCode);
	 return output;
	 }

	


}

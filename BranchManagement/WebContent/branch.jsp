<%@page import="model.Branch"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>Branch Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/branch.js"></script>
</head>
<body>
<div class="container my-5">
    <div class="card border-secondary p-4">
      <div class="card-header bg-info">
        <h4 class="text-center text-light">Add Branch Details</h4>
      </div>
	<form id="formBranch" name="formBranch">
	<div class="form-group">
	<br>
		 Branch ID:
		<input id="branch_id" name="branch_id" type="text" class="form-control">
		
		<br> Branch Name
		<input id="branch_name" name="branch_name" type="text" class="form-control">
		
		<br> Branch Address:
		<input id="branch_address" name="branch_address" type="text" class="form-control">
		
		<br> District:
		<input id="district" name="district" type="text" class="form-control">
		<br>
		
		<br> Cover Areas:
		<input id="cover_areas" name="cover_areas" type="text" class="form-control">
		<br>
		
		<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
		<input type="hidden" id="hidBranchIDSave"name="hidBranchIDSave" value="">
		</div>
	</form>
</div>

<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>

<br>
<div id="divItemsGrid">

<div class="container my-5">
    <div class="card border-secondary p-4">
      <div class="card-header bg-info">
        <h4 class="text-center text-light">Branch Details</h4>
      </div>	
	
	<%
		Branch branchObj = new Branch();
		out.print(branchObj.readBranch());
	%>
</div>	
</div></div></div>
</body>
</html>
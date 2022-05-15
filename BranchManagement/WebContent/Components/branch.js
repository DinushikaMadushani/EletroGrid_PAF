$(document).ready(function() {

	$("#alertSuccess").hide();
	$("#alertError").hide();

});

// CLIENT-MODEL================================================================
function validateItemForm()
{
	// ID
	if ($("#branch_id").val().trim() == "")
	{
	return "Insert branch Id.";
	}
	
	// NAME
	if ($("#branch_name").val().trim() == "")
	{
	return "Insert branch Name.";
	}
	
	// Address
	if ($("#branch_address").val().trim() == "")
	{
	return "Insert branch Address.";
	}
	
	//District
	if ($("#district").val().trim() == "")
	{
	return "Insert District";
	}
	
	// Cover Areas
	if ($("#cover_areas").val().trim() == "")
	{
	return "Insert cover areas.";
	}
	
return true;
}
//save btn

$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
		// Form validation-------------------
	
		var status = validateItemForm();
		if (status != true)
		{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
		}
		// If valid------------------------
		var type = ($("#hidBranchIDSave").val() == "") ? "POST" : "PUT";
		$.ajax(
			{
				url : "BranchAPI",
				type : type,
				data : $("#formBranch").serialize(),
				dataType : "text",
				complete : function(response, status)
			{
			onItemSaveComplete(response.responseText, status);
			}
		});
});
//Save fun
function onItemSaveComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
	{
		$("#alertSuccess").text("Successfully saved.");
		$("#alertSuccess").show();
		$("#divItemsGrid").html(resultSet.data);
	} else if (resultSet.status.trim() == "error")
	{
		$("#alertError").text(resultSet.data);
		$("#alertError").show();
	}
	} else if (status == "error")
	{
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else
		{
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
		$("#hidBranchIDSave").val("");
		$("#formBranch")[0].reset();
}





//update click
$(document).on("click", ".btnUpdate", function(event)
{
	$("#hidBranchIDSave").val($(this).data("branchCode"));
	$("#branch_id").val($(this).closest("tr").find('td:eq(0)').text());
	$("#branch_name").val($(this).closest("tr").find('td:eq(1)').text());
	$("#branch_address").val($(this).closest("tr").find('td:eq(2)').text());
	$("#district").val($(this).closest("tr").find('td:eq(3)').text());
	$("#cover_areas").val($(this).closest("tr").find('td:eq(4)').text());
});

//delete click
$(document).on("click", ".btnRemove", function(event)
{
	$.ajax(
		{
			url : "BranchAPI",
			type : "DELETE",
			data : "branchCode=" + $(this).data("branchCode"),
			dataType : "text",
			complete : function(response, status)
		{
			onItemDeleteComplete(response.responseText, status);
		}
	});
});
//delete fun
function onItemDeleteComplete(response, status)
{
if (status == "success")
{
	var resultSet = JSON.parse(response);
	if (resultSet.status.trim() == "success")
	{
		$("#alertSuccess").text("Successfully deleted.");
		$("#alertSuccess").show();
		$("#divItemsGrid").html(resultSet.data);
	} else if (resultSet.status.trim() == "error")
		{
		$("#alertError").text(resultSet.data);
		$("#alertError").show();
	}
	} else if (status == "error")
	{
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else
	{
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
	
}

















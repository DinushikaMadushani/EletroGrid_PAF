package model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BranchAPI")
public class BranchAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Branch branchObj = new Branch();
    public BranchAPI() {
        super();
        
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String output = branchObj.insertBranch(request.getParameter("branch_id"),
				request.getParameter("branch_name"),
				request.getParameter("branch_address"),
				request.getParameter("district"),
				request.getParameter("cover_areas"));
				
		response.getWriter().write(output);
	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map  paras = getParasMap(request);
		String output = branchObj.updateBranch(paras.get("hidBranchIDSave").toString(),
							paras.get("branch_id").toString(),
							paras.get("branch_name").toString(),
							paras.get("branch_address").toString(),
							paras.get("district").toString(),
							paras.get("cover_areas").toString());
		
		response.getWriter().write(output);
	}


	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		String output = branchObj.deleteBranch(paras.get("branchCode").toString());
		
		response.getWriter().write(output);
	}

	
	// Convert request parameters to a Map
		private static Map<String, String> getParasMap(HttpServletRequest request)
		{
			Map<String, String> map = new HashMap<String, String>();
			try
			{
				Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
				String queryString = scanner.hasNext() ?
									scanner.useDelimiter("\\A").next() : "";
				scanner.close();
				
				String[] params = queryString.split("&");
				for (String param : params)
				{
					String[] p = param.split("=");
					map.put(p[0], p[1]);
				}
			}
			catch (Exception e)
			{
			}
			return map;
				
			}
}



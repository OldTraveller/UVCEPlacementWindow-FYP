<%@page import="java.util.Arrays"%>
<%@page import="java.sql.Array"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.ProblemRecommender"%>
<%@page import="POJOS.CodeforcesProblem"%>
<%@page import="java.util.HashSet"%>
<%@page import="Model.PostModel"%>
<%@page import="Model.DatabaseConnection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.net.URISyntaxException"%>

<%
	try{
		String id = request.getParameter("rating");
		HashSet<CodeforcesProblem> problems = ProblemRecommender.getProblemsByRatingFromTree(Long.parseLong(id));
		int i = 0; 
		for (CodeforcesProblem problem : problems) {
			i++;
				String color = ""; 
				if (problem.getRating() < 1000) {
					color = "green";  
				} else if (problem.getRating() < 1500) {
					color = "blue"; 
				} else if (problem.getRating() < 2000) {
					color = "orange"; 
				} else {
					color = "red"; 
				}
				
				StringBuilder displayTags = new StringBuilder(); 
				for (String tag_in : problem.getTags()) {
					displayTags.append(tag_in + ","); 
				}
				displayTags.setLength(displayTags.length() - 1);
				String html = "<div class='col-sm-12 col-md-3 col-lg-3'>" + 
					    		"<div class='card'>" +
			      					"<div class='card-body'>" +
			        					"<h4 class='card-title'><strong>" + problem.getName() + "</strong></h5>" +
			        					"<p class='card-text' style='color: " + color + "'>Difficulty - " + problem.getIndex() + "</p>" +
			        					"<p class='card-text'>Total Solve Count - " + problem.getTotalSolveCount() + "</p>" +
			        					"<p class='card-text' style='color: purple'>" + displayTags.toString() + "</p>" +
			        					"<a target='_blank' href='" + problem.getUrl() + "'> Problem Link </a>" +
			      					"</div>" +
			   					"</div>" + 
			      			"</div>";
			  			out.println(html);
		}
		out.println("</div>");
		if (i == 0) {
			out.println("<h3 align='center' style='color: red;'><strong>NO POSTS PRESENT !!</strong></h3>");
		} 
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
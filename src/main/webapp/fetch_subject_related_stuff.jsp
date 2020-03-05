<%@page import="Model.DatabaseConnection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.net.URISyntaxException"%>

<%
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
	try{
		String id = request.getParameter("id");
		connection = DatabaseConnection.getLocalConnection(); 
		statement=connection.createStatement();
		String sql = ""; 
		if (Long.parseLong(id) == 0) {
			sql = "SELECT * FROM POSTS WHERE POST_ISSPAM = 0"; 
		} else {
			sql = "SELECT * FROM POSTS WHERE POST_SUBJECT_ID = " + id + " AND POST_ISSPAM = 0";
		}
		System.out.println("QUERY IS : " + sql); 
		resultSet = statement.executeQuery(sql);
		int i = 0; 
		out.println("<hr>");
		out.println("<div id='accordion'");
		while(resultSet.next()){
				i++;
				long postId = resultSet.getLong("POST_ID");
				String postName = resultSet.getString("POST_NAME"); 
				String postDescription = resultSet.getString("POST_DESC");
				String link = resultSet.getString("POST_LINK"); 
				int subjectId = resultSet.getInt("POST_SUBJECT_ID");
				String signedBy = resultSet.getString("STUDENT_USN");
				String color = ""; 
				if (subjectId == 1) {
					color = "red"; 
				} else if (subjectId == 2) {
					color = "orange"; 
				} else if (subjectId == 3) {
					color = "blue"; 
				} else if (subjectId == 4) {
					color = "black"; 
				} else if (subjectId == 5) {
					color = "purple"; 
				}
				out.println("<div class='card'>");
				String html = 
						"<div class='panel-group' id='accordion'>" + 
						    "<div class='panel panel-default'>" + 
							    "<div class='panel-heading'>" + 
								    "<h4 class='panel-title'>" + 
									    "<h4><a style='color: black' data-toggle='collapse' data-parent='#accordion' href='#collapse" + i + "' >" + postName + 
									    "</a> (<span style='color: " + color + "'> SUBJECT ID : " + subjectId +"</span> ) </h4>" + 
								    "</h4>" + 
								    "</div>" + 
								    "<div id='collapse" + i + "' class='panel-collapse collapse in'>" + 
									    "<div class='panel-body'>" + 
									    		postDescription + 
							      		"</div>" + 
									    "<h6 style='color: green'><u>DIGITALLY SIGNED BY : " + signedBy + "</u></h6>" + 
									    "<h6 style='color: #00cc00'>POST_ID : " + postId + "</h6>" + 
									    "<h6>LINK : <a style='color: blue;' href='"+ link +"' target='_blank'>" + link + 
								        "<script src='" + link + "'></script></a></h6>" + 
								        "<strong><h6 style='color: #006600'>The post is SpamFree.</h6></strong>" + 
									"</div>" + 
				   				"</div>" + 
			  				"</div>" + 
			  			"</div>";
			  			out.println(html);
			  			out.println("</div>");
		}
		out.println("</div>");
		if (i == 0) {
			out.println("<h3 align='center' style='color: red;'><strong>NO POSTS PRESENT !!</strong></h3>");
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		statement.close(); 
		connection.close();
	}
%>
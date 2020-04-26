<%@page import="Model.ApplicationConstants"%>
<%@ include file = "/partials/header.jsp" %>	
<%@page import="Model.DatabaseConnection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.net.URISyntaxException" %>
<%@page import="java.sql.SQLException"%>
    <section class="container totn-border w3-animate-right">
        <section class="hero is-black">
            <div class="hero-body" >
                <div class="container" align="center">
                    <h1 ><strong>
                        <span style="color: BLACK;">Contribute just by the DIGITAL SIGNATURE</span>
                    </strong></h1>
                    <h3 class="subtitle"> <span style="color: BLACK;">
                    	No Login and Registration required as in the typical web applications!
                    	</span>
                    </h3>
                </div>
            </div>
        </section>
    </section>
    <hr>
    <section class="container w3-animate-zoom" align="center">
  			<h2><strong>POST Information Here</strong></h2>
  			<hr>
<%
	if (request.getServletContext().getAttribute("STATUS") != null) {
		int status = (Integer)request.getServletContext().getAttribute("STATUS"); 
		if (status == 0) { %>
			<div class="alert alert-success alert-dismissible fade show" role="alert">
						  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
						    <span aria-hidden="true">&times;</span></button>
			  <strong>Success!</strong> <%= ApplicationConstants.getStatusMessages().get(status) %>
			</div>
		<% } else if (status == 1) {  %>
			<div class="alert alert-warning alert-dismissible fade show" role="alert">
						  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
						    <span aria-hidden="true">&times;</span></button>
			  <strong>Warning!</strong> <%= ApplicationConstants.getStatusMessages().get(status) %>
			</div>
		<% } else if (status == 2) { %>
			<div class="alert alert-primary alert-dismissible fade show" role="alert">
						  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
						    <span aria-hidden="true">&times;</span></button>
			 	 <%= ApplicationConstants.getStatusMessages().get(status) %>
			</div>
		<% }
	} request.getServletContext().setAttribute("STATUS", null);
%>
	  		<section class="container" align="center">
	    		<div class="row">
	    			<div class="col-md-3"></div>
	    			<div class="col-md-6">
	    			<form action="InsertPostServlet" method="POST">
						  <div class="form-group">
						  	<h3>TOPIC NAME</h3>
						    <input type="text" class="form-control" name="TOPIC_NAME" placeholder="TOPIC NAME" required> 
						  </div>
						  <div class="form-group">
						  	<h3>LINK</h3>
						    <input type="text" class="form-control" name="LINK" placeholder="LINK IF ANY ..." required>
						  </div>
						  <div class="form-group">
						  	<h3>SUBJECT</h3>
						    <p class="control">
							    <span class="select" >
							      <select name="SUBJECT_ID" required>
							        <option value="1">DATA STRUCTURES</option>
							        <option value="2">ALGORITHMS</option>
							        <option value="3">OPERATING SYSTEMS</option>
							        <option value="4">DATABASE MANAGEMENT SYSTEMS</option>
							        <option value="5">OTHERS</option>
							      </select>
							    </span>
							  </p>
						  </div>
						  <div class="form-group">
						  	<h3>DESCRIPTION</h3>
    						<textarea class="form-control" rows="8" name="DESCRIPTION" required></textarea>
						  </div>
						  <button type="submit" class="btn btn-dark">POST with Digital Signature</button>
						</form>
	    			</div>
	    			<div class="col-md-3"></div>
	    		</div>
	    	</section>
    </section>

    <hr>
<section class="container w3-animate-zoom" align="center">
  			<h3><strong>CONTRIBUTION MADE TILL DATE<strong></h3>
  			<hr>
<%!
	public static String getDataElement(String one, String two, String three, int four) {
		String fourThing = ""; 
		if (four == 0) {
			fourThing = "<span><p style=\"color:green\">SPAM FREE</p></span>"; 
		} else {
			fourThing = "<span><p style=\"color:orange\">SPAM PRONE</p></span>"; 
		}
		return "<tr>" + 
			   "<td><strong>" + one + "</strong></td> " + 
			   "<td>" + two + "</td>" +  
			   "<td>" + three + "</td>" +
			   "<td>" + fourThing + "</td>" + 
			   "</tr>";
	}
%>
<%
	Connection connection = DatabaseConnection.getLocalConnection();
	Statement statement = null;
	ResultSet resultSet = null;
	try{
		statement = connection.createStatement();
		String sql ="SELECT * FROM POSTS";
		System.out.println("QUERY IS : " + sql); 
		resultSet = statement.executeQuery(sql);
		int i = 0; 
		out.println("<hr>");
		out.println("<table class='table table-bordered table-hover'>" + 
				"<thead>" + 
				"<tr>" + 
				"<th style='background-color: BLACK; color: white'>POST ID</th>" + 
				"<th style='color : white; background-color: BLACK;'>POST NAME</th>" + 
				"<th style='color : white; background-color: BLACK;'>CONTRIBUTED BY</th>" + 
				"<th style='color : white; background-color: BLACK;'>STATUS</th>" + 
				"</tr>" + 
				"</thead><tbody>");
		while(resultSet.next()){
				i++; 
				String postId = resultSet.getString("POST_ID"); 
				String postName = resultSet.getString("POST_NAME"); 
				String contributedBy = resultSet.getString("STUDENT_USN"); 
				int status = resultSet.getInt("POST_ISSPAM"); 	
				out.println(getDataElement(postId, postName, contributedBy, status));
		}
		out.println("<tbody></table>");
		if (i == 0) {
			out.println("<h3 align='center' style='color: red;'><strong>SORRY BUT NO RECORD WAS FOUND WITH THIS DATA !!</strong></h3>");
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		statement.close(); 
		connection.close(); 
	}
%>  			
    </section>
    <hr>

<jsp:include page="./partials/footer.jsp" />

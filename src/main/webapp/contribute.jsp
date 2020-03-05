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
                        <span style="color: #003366;">Contribute just by the DIGITAL SIGNATURE</span>
                    </strong></h1>
                    <h3 class="subtitle"> <span style="color: #003366;">
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
	  		<section class="container" align="center">
	    		<div class="row">
	    			<div class="col-md-3"></div>
	    			<div class="col-md-6">
	    			<form action="AddTopic" method="POST">
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
						  <div class="form-group">
							  <label for="myfile">SELECT DIGITAL SIGNATURE</label>
							  <input type="file" id="myfile" name="myfile">
						  </div>
						  <button type="submit" class="btn btn-primary">Submit</button>
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
		System.out.println("SIZE IS : " + resultSet.getFetchSize());
		out.println("<table class='table table-bordered table-hover'>" + 
				"<thead>" + 
				"<tr>" + 
				"<th style='background-color: #003366; color: white'>POST ID</th>" + 
				"<th style='color : white; background-color: #003366;'>POST NAME</th>" + 
				"<th style='color : white; background-color: #003366;'>CONTRIBUTED BY</th>" + 
				"<th style='color : white; background-color: #003366;'>STATUS</th>" + 
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

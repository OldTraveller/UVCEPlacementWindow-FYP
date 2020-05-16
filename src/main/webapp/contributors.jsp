<%@page import="Model.DatabaseConnection"%>
<%@ include file = "/partials/header.jsp" %>	
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
                        <span style="color: BLACK;">List of PEOPLE</span>
                    </strong></h1>
                    <h3 class="subtitle"> <span style="color: BLACK;">
                    	List of people who can make the contribution as of now. 
                    	If interested to use the UVCE Placement Window Tool personalized version, contact us. 
                    	</span>
                    </h3>
                </div>
            </div>
        </section>
    </section>
    <section class="container w3-animate-zoom" align="center">
<%!
	public static String getDataElement(String name, String branch, String codeforces, String gitHub) {
		String gitHubUrl = "https://github.com/" + gitHub; 
		String codeForcesUrl = "https://codeforces.com/profile/" + codeforces; 
		return "<tr>" + 
				"<td class='is-half'><strong>" + name + "</strong></td>" + 
				"<td class='is-half' style='color: blue;'>" + branch + "</td>" + 
						"<td class='is-half' style='color: purple;'><a target='_blank' href='" + codeForcesUrl + "'>" + codeforces + "</a></td>" + 
								"<td class='is-half' style='color: cyan;'><a target='_blank' href='" + gitHubUrl + "'>" + gitHub + "</a></td>" + 
				"</tr>";
	}
%>
<%
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
	try{
		connection = DatabaseConnection.getLocalConnection(); 
		statement=connection.createStatement();
		String sql ="SELECT * FROM STUDENT_DATA WHERE STUDENT_ISVALID = 1";
		System.out.println("QUERY IS : " + sql); 
		resultSet = statement.executeQuery(sql);
		int i = 0; 
		out.println("<hr>");
		out.println("<table class='table table-bordered table-hover'>" + 
				"<thead>" + 
				"<tr>" + 
				"<th style='background-color: BLACK; color: white'>NAME</th>" + 
				"<th style='color : white; background-color: BLACK;'>BRANCH</th>" + 
								"<th style='color : white; background-color: BLACK;'>CODEFORCES</th>" + 
										"<th style='color : white; background-color: BLACK;'>GITHUB</th>" + 
				"</tr></thead>");
		
		while(resultSet.next()){
				i++; 
				String branch = resultSet.getString("STUDENT_BRANCH"); 
				String name = resultSet.getString("STUDENT_NAME"); 
				String codeforces = resultSet.getString("STUDENT_CODEFORCES_HANDLE"); 
				String gitHub = resultSet.getString("STUDENT_GITHUB_HANDLE"); 
				out.println(getDataElement(name, branch, codeforces, gitHub)); 
		}
		out.println("</table>");
		if (i == 0) {
			out.println("<h3 align='center' style='color: red;'><strong>No records present currently.</strong></h3>");
		}
		connection.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
%>  			
    </section>
    <hr>
<jsp:include page="./partials/footer.jsp" />

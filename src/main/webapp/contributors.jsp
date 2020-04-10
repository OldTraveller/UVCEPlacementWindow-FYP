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
                        <span style="color: BLACK;">List Of Public Keys of Participants ...</span>
                    </strong></h1>
                    <h3 class="subtitle"> <span style="color: BLACK;">
                    	List of people who can make the contribution as of now. If interested for a key, generate it from the menu. 
                    	</span>
                    </h3>
                </div>
            </div>
        </section>
    </section>
    <hr>

    <section class="container w3-animate-zoom" align="center">
  			<h3><strong>LIST OF PEOPLE AND THEIR PUBLIC KEYS<strong></h3>
  			<hr>
<%!
	public static String getDataElement(String key, String value) {
		return "<tr><td class='is-half'><strong>" + key + "</strong></td><td class='is-half' style='color: blue;'>" + value + "</td></tr>";
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
				"<thead><tr><th style='background-color: BLACK; color: white'>NAME</th><th style='color : white; background-color: BLACK;'>PUBLIC KEY</th></tr></thead>");
		
		while(resultSet.next()){
				i++; 
				String publicKey = resultSet.getString("STUDENT_PUBLIC_KEY"); 
				String name = resultSet.getString("STUDENT_NAME"); 
				out.println(getDataElement(name, publicKey)); 
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

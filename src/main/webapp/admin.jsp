<%@page import="POJOS.StudentData"%>
<%@page import="Model.DigitalSignatureModel"%>
<%@page import="Model.ApplicationConstants"%>
<%@page import="Model.StudentModel"%>

<%@ include file = "/partials/header.jsp" %>	
    <section class="container totn-border w3-animate-right">
        <section class="hero is-black">
            <div class="hero-body" >
                <div class="container" align="center">
                    <h1 ><strong>
                        <span style="color: #003366;">Personalized Content</span>
                    </strong></h1>
                    <h3 class="subtitle"> <span style="color: #003366;">
                    		VALUABLE INFORMATION
                    	</span>
                    </h3>
                </div>
            </div>
        </section>
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
		<% } else { %>
			<div class="alert alert-info alert-dismissible fade show" role="alert">
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span></button>
			<%= ApplicationConstants.getStatusMessages().get(status) %>
		</div>
		<% }
	} request.getServletContext().setAttribute("STATUS", null);
%>
    </section>
    <hr>
    <% if (!DigitalSignatureModel.isDigitalSignaturePresent()) { %>
    <div class="hero-unit container totn-border w3-animate-right" align="center">
	  <h1 style="color: red;">Digital Signature Error!</h1>
	  <p style="color: orange;">There might not be a valid Digital Signature Device present in the PC.</p>
	  <p>
	    <a class="btn btn-primary btn-large" href="AdminView">
	      CheckAgain!
	    </a>
	  </p>
	</div>
    <% } else { %>
    <div class="container"> 
    	<jsp:include page="./normal.jsp" />
  	 </div>
    <% } %>
<jsp:include page="./partials/footer.jsp" />

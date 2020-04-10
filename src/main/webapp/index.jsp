<jsp:include page="./partials/header.jsp" />
<%@page import="Model.ApplicationConstants"%>
	<!-- WHY US ? -->
	<hr>
		<section class="fp-active" data-block-type="contents">
		  <div class="container">
		    <div class="row align-items-center">
<%
	if (request.getServletContext().getAttribute("STATUS") != null) {
		int status = (Integer)request.getServletContext().getAttribute("STATUS"); 
		if (status == 0) { %>
			<div class="alert alert-succcess alert-dismissible fade show" role="alert">
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
		      <div class="col-12 col-md-12 col-lg-6 col-xl-5">
		        <h2 class="text-center bolder w3-animate-bottom">About UVCE Placement Window ...</h2>
		        <p class="lead mb-5 w3-animate-top">
					Campus Placements are one of the most important parts of college life of a student. 
					UVCE Placement window is aimed at giving the students of UVCE, a window to the actual scenario 
					as to how things proceed in the placements. <br>
					The traditional approach followed in most of the web sites out there in order to post something is having 
					a SignUp and Login interface. <br>
				</p>
		      </div>
		      <div class="col-12 col-md-6 col-sm-12 m-auto">
		        <img alt="We Are All Together !!" class="img-fluid black-border" src="images/MainPageImage.png" >
		      </div>
		    </div>
		  </div>
		</section>
		<hr> 
		<section class="fp-active" data-block-type="contents">
		  <div class="container">
		 <div class="row align-items-center">
		      <div class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
		        <h2 class="text-center bolder w3-animate-bottom">Concepts and Technology used from Engineering at UVCE</h2>
					<ul class="list-group">
					  <li class="list-group-item text-center">Digital Signature (Network Security 6th Semester)</li>
					  <li class="list-group-item text-center">Authentication and Authorization (Network Security)</li>
					  <li class="list-group-item text-center">Java Programming language (JAVA Labs 3rd Semester)</li>
					  <li class="list-group-item text-center">Spam Detector at BackEnd (Data Mining - Naive Bayesian Classifier)</li>
					</ul>
		      </div>
		    </div>
		  </div>
		</section>
	<hr>

<jsp:include page="./partials/footer.jsp" />
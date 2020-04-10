<%@page import="Model.PostModel"%>
<%@ include file = "/partials/header.jsp" %>	
<%@page import="POJOS.Post"%>
<%@page import="Model.PostModel"%>
<%@page import="java.util.ArrayList"%>
<%
	Post post = (Post)request.getServletContext().getAttribute("POST_OBJECT"); 
%>
 <section class="container totn-border w3-animate-right">
        <section class="hero is-black">
            <div class="hero-body" >
                <div class="container" align="center">
                    <h1 ><strong>
                        <span style="color: BLACK;">EDITING POST</span>
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
  			<section class="container">
	    		<div class="row">
	    			<div class="col-md-3"></div>
	    			<div class="col-md-6">
	    			<form action="EditPostServlet" method="POST">
						  <div class="form-group">
						  	<h3>TOPIC NAME</h3>
						    <input value="<%= post.getPostName() %>" type="text" class="form-control" name="TOPIC_NAME" placeholder="TOPIC NAME" required> 
						  </div>
						  <div class="form-group">
						  	<h3>LINK</h3>
						    <input value="<%= post.getPostLink() %>" type="text" class="form-control" name="LINK" placeholder="LINK IF ANY ..." required>
						  </div>
						  <div class="form-group">
						  	<h3>SUBJECT</h3>
						    <p class="control">
							    <span class="select" >
							      <select name="SUBJECT_ID" required>
							      <% ArrayList<String> subjects = PostModel.getSubjectNamesArrayList(); 
							      	for (int i = 1; i < subjects.size(); i++) {
							      %>
							        <option value="<%= i %>" <% if (post.getPostSubjectId() == i) out.print("selected"); %>><%= subjects.get(i) %></option>
							      <% } %>
							      </select>
							    </span>
							  </p>
						  </div>
						  <div class="form-group">
						  	<h3>DESCRIPTION</h3>
    						<textarea class="form-control" rows="8" name="DESCRIPTION" required><%= post.getPostDesc() %></textarea>
						  </div>
						  <button type="submit" class="btn btn-danger">EDIT POST with Digital Signature</button>
						</form>
	    			</div>
	    			<div class="col-md-3"></div>
	    		</div>
	    	</section>
    </section>
    <hr>
<jsp:include page="./partials/footer.jsp" />
   <%@page import="Model.PostModel"%>
<%@page import="POJOS.Post"%>
<%@page import="java.util.ArrayList"%>
<%@page import="POJOS.StudentData"%>
<%@page import="Model.DigitalSignatureModel"%>
<%@page import="Model.StudentModel"%>

<%
	StudentData studentData = StudentModel.getDigitalSignaturePresentStudentData();
	ArrayList<Post> posts = PostModel.getAllPostsFromDatabaseForUSN(); 
	ArrayList<Post> otherPosts = PostModel.getAllPostsFromDatabase();
	/* 
		The below line makes sure that the ADMIN is not able to toggle his SPAM status. 
		Only the otherPosts, the ADMINS will be able to modify. So we display only these posts there. 
		
		SET DIFFERENCE OPERATION done here.
	*/ 
	int spamCount = 0; 
%> 
   
   <div id="menu2" class="container tab-pane">
    <h3 class="text-center"><strong>SPAM/HAM Visualizer and Toggler</strong></h3>
    <hr>
    <div class="container">
    	<div class="row">
    		<div class="col-12 col-sm-12 col-md-6 col-lg-6">
    			<h3 style='color: red' class='text-center'>SPAM QUEUE</h3>
    			<div class="accordion" id="accordionExample">
    			<!-- THIS IS THE PART TO LOOP UPON -->
    	<% for (Post post : otherPosts) { 
    		if (post.isPostIsSpam()) {
    			spamCount++; 
    	%>
<div class="container" style="padding: 2px;">
		<button type="button" style="width: 100%;" class="btn btn-danger" data-toggle="modal" data-target="#exampleModalCenter<%= post.getPostId() %>">
		 	<%= post.getPostName() %>
		</button>
<!-- Modal -->
<div class="modal fade" id="exampleModalCenter<%= post.getPostId() %>" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenter<%= post.getPostId() %>Title" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <strong><h4 class="modal-title" id="exampleModal<%= post.getPostId() %>LongTitle"><%= post.getPostName() %></h4></strong>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
     	 <h4><strong>LINK : </strong> <a target="__blank" href="<%= post.getPostLink() %>"><%= post.getPostLink() %></a></h4>
        <%= post.getPostDesc() %>
      </div>
      <div class="modal-footer" style="width: 100%;">
        <form action = "TogglePostSpamStatusServlet" method="POST" onsubmit = "return confrimToggle('<%= post.getPostName() %>')">
        	<button style="width: 100%;" type="submit" name="POST_ID" value="<%= post.getPostId() %>" class="btn btn-success">Toggle</button>
        </form>
        </div>
    </div>
    </div>
  </div>
</div>
<% 		}
    		}%>
    	</div>
    		</div>
    		<div class="col-12 col-sm-12 col-md-6 col-lg-6">
    			<h3 style='color: green' class='text-center'>NO SPAM QUEUE</h3>
    			<!-- THIS IS THE PART TO LOOP UPON -->
    	<% for (Post post : otherPosts) { 
    		if (!post.isPostIsSpam()) {
    	%>
<div class="container" style="padding: 2px;">
<!-- Button trigger modal -->
		<button type="button" style="width: 100%;" class="btn btn-success" data-toggle="modal" data-target="#exampleModalCenter<%= post.getPostId() %>">
		 	<%= post.getPostName() %>
		</button>
<div class="modal fade" id="exampleModalCenter<%= post.getPostId() %>" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenter<%= post.getPostId() %>Title" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
         <strong><h4 class="modal-title" id="exampleModal<%= post.getPostId() %>LongTitle"><%= post.getPostName() %></h4></strong>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      	<h4><strong>LINK : </strong> <a target="__blank" href="<%= post.getPostLink() %>"><%= post.getPostLink() %></a></h4>
        <%= post.getPostDesc() %>
      </div>
      <div class="modal-footer">
        <form action = "TogglePostSpamStatusServlet" method="POST" onsubmit = "return confrimToggle('<%= post.getPostName() %>')">
        	<button style="width: 100%;" type="submit" name="POST_ID" value="<%= post.getPostId() %>" class="btn btn-danger">Toggle</button>
        </form>
        </div>
    </div>
    </div>
  </div>
</div>
    	<% 		}
    		}%>
    		</div>
    	</div>
    	<hr>
    	<h2 class="text-center"><strong>[ <span style='color: red'>SPAM</span> : <span style='color: green'>HAM</span> ] => [ <span style='color: red'><%= spamCount %></span> : <span style='color: green'><%= otherPosts.size() - spamCount %></span> ]</strong></h2>
 	 </div>
  	<hr>
  </div>
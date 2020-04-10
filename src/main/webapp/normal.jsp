<%@page import="Model.PostModel"%>
<%@page import="POJOS.Post"%>
<%@page import="java.util.ArrayList"%>
<%@page import="POJOS.StudentData"%>
<%@page import="Model.DigitalSignatureModel"%>
<%@page import="Model.StudentModel"%>
<%
	StudentData studentData = StudentModel.getDigitalSignaturePresentStudentData();
	ArrayList<Post> posts = PostModel.getAllPostsFromDatabaseForUSN(); 
	/* 
		The below line makes sure that the ADMIN is not able to toggle his SPAM status. 
		Only the otherPosts, the ADMINS will be able to modify. So we display only these posts there. 
		SET DIFFERENCE OPERATION done here.
	*/ 
	int spamCount = 0; 
%> 

<h1 class="text-center">Welcome, <span style='color: blue'><%= studentData.getStudentName() %></span> !!!</h1>
<hr>
<div class="container" class="text-center">
<ul class="nav nav-pills" role="tablist">
  <li class="nav-item">
    <a class="nav-link active" data-toggle="pill" href="#home">Posts and Statistics</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" data-toggle="pill" href="#menu1">Codeforces Profile</a>
  </li>
  
 <% if (StudentModel.isAdmin()) { %>
   <li class="nav-item">
    <a class="nav-link" data-toggle="pill" href="#menu2">SPAM Queue Visualizer</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" data-toggle="pill" href="#menu3">Generate New User</a>
  </li>
 <% } %>
</ul>
</div>
<hr>
<div class="tab-content">
  <div id="home" class="container tab-pane active">
    	<div class="container">

<div class="row">
	<div class="col-sm-12 col-md-6 col-lg-6">
		<div id="piechart_3d" style="width: 100%; height: 100%;"></div>
	</div>
	<div class="col-sm-12 col-md-6 col-lg-6">
		<table class="table table-hover">
	  <thead>
	    <tr>
	      <th scope="col"><h4>PARAMETERS</h4></th>
	      <th scope="col"><h4>DATA</h4></th>
	    </tr>
	  </thead>
	  <tbody>
	    <tr>
	      <td style="color: Purple"><strong>USN</strong></td>
	      <td><strong><%= studentData.getStudentUSN() %></strong></td>
	    </tr>
	    <tr>
	      <td style="color: Purple"><strong>NAME</strong></td>
	      <td><strong><%= studentData.getStudentName() %></strong></td>
	    </tr>
	    <tr>
	      <td style="color: Purple"><strong>BRANCH</strong></td>
	      <td><strong><%= studentData.getStudentBranch()%></strong></td>
	    </tr>
		<tr>
	      <td style="color: Purple"><strong>SEMESTER</strong></td>
	      <td><strong><%= studentData.getStudentSemester()%></strong></td>
	    </tr>
	    <tr>
	      <td style="color: Purple"><strong>EMAIL</strong></td>
	      <td><strong><a href="mailto:<%= studentData.getStudentEmail() %>"><%= studentData.getStudentEmail()%></a></strong></td>
	    </tr>
	    <tr>
	      <td style="color: Purple"><strong>PUBLIC KEY</strong></td>
	      <td style="color: red"><strong><%= studentData.getStudentPublicKey() %></strong></td>
	    </tr>
	  </tbody>
	</table>
	</div>
</div>
<hr>
	<h3 class="text-center">POSTS ASSOCIATED WITH USN - <span style='color: blue'><%= studentData.getStudentUSN() %></span></h3>
	<table class="table table-bordered">
  <thead>
    <tr>
      <th scope="col">POST_ID</th>
      <th scope="col">POST_HEADING</th>
      <th scope="col">POST_SUB</th>
    </tr>
  </thead>
  <tbody>
  <% for (Post post : posts) { 
  		String color = ""; 
  		if (post.isPostIsSpam()) {
  			color = "orange"; 
  			spamCount++; 
  		} else {
  			color = "green"; 
  		}
  %>
    <tr>
      <td><a target="__blank" href="ViewEditPostServlet?postId=<%= post.getPostId() %>"><%= post.getPostId() %></a></td>
      <td><strong><span style="color: <%= color %>"><%= post.getPostName() %></span></strong></td>
      <td><%= PostModel.getSubjectNamesArrayList().get(post.getPostSubjectId()) %></td>
    </tr>
  <% } %>
  </tbody>
</table>
    	</div>
    	<hr>
<div class="container">
	<h2 class="text-center" style='color: blue'><strong>TOTAL POST COUNT : <%= posts.size() %></strong></h2>
</div>
<hr>
  </div>
  <div id="menu1" class="container tab-pane">
    <h3 class="text-center">Codeforces Profile of <span style="color: purple"><strong><%= studentData.getStudentName() %></strong></span></h3>
  	<hr>
  	<% if (studentData.getStudentCodeforcesHandle().equals("#")) { %>
  		<h2 class="text-center" style='color: red'><strong>Sorry there is no Codeforces Handle associated with this account!</strong></h2>
  	<% } else { %>
  		<h4 class="text-center" style='color: blue'>Codeforces Handle: <strong><%= studentData.getStudentCodeforcesHandle() %></strong></h4>
  		    	<hr>
  		    	<jsp:include page="./codeforces_page.jsp" />
  	<% } %>
  	<hr>
  </div>
<% if (StudentModel.isAdmin()) { %>
	<jsp:include page="./admin_task.jsp" />
<% } %>
</div>

<script type="text/javascript">
      google.charts.load("current", {packages:["corechart"]});
      google.charts.setOnLoadCallback(drawChart);
      
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Task', 'Hours per Day'],
          ['SPAM',   <%= spamCount %>],
          ['NO-SPAM', <%= posts.size() - spamCount %> ]
        ]);

        var options = {
          title: 'Posts related to <%= studentData.getStudentUSN() %>',
          is3D: true,
          slices: {
              0: { color: 'ORANGE' },
              1: { color: 'GREEN' }
          }
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
        chart.draw(data, options);
      }
      
      function confrimToggle(postName) {
    	  if (confirm("Do you want to toggle the SPAM status for : " + postName)) {
    		  return true; 
    	  }
    	  return false; 
      }
      
      function sendToggleRequest(postId) {
    	  $.get("TogglePostSpamStatusServlet?POST_ID=" + postId, function(data, status){
    		  alert
    	  });
      }
</script>
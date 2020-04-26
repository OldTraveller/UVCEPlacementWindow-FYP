<%@page import="POJOS.StudentData"%>
<%@page import="POJOS.GithubRepository"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.StudentModel"%>
<%@page import="Model.GithubAPI"%>
<head>
  <link rel="stylesheet" href="styles/style.css">
  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  <script src="https://code.getmdl.io/1.3.0/material.min.js"></script>
  <script src="js/single.js"></script>
</head>
<%
	StudentData studentData = StudentModel.getDigitalSignaturePresentStudentData();
	ArrayList<GithubRepository> repositories = GithubAPI.getGithubRepositoriesOfUser(studentData);
%>
	<section>
		<div class="row">
			<section class="container">
			<h3 class="text-center" align="center">Total Repository Count - <%= repositories.size() %></h3>
				<div class="list-group" id="repositoryList">
				<% for (GithubRepository repository : repositories) { %>
					<div class="list-group-item list-group-item-action flex-column align-items-start">
						<div class="d-flex w-100 justify-content-between">
							<h3 class="mb-1"><%= repository.getRepositoryFullName() %></h3>
							<small>Public</small>
						</div>
						<% if (repository.getRepositoryDescription() != null) { %>
							<div class="d-flex w-100 justify-content-between">
								<h5 class="mb-1"><%= repository.getRepositoryDescription() %></h5>
							</div>
						<% } %>
						<a href="<%= repository.getRepositoryHTMLUrl() %>" target="_blank" class="mb-1"><%= repository.getRepositoryHTMLUrl() %></a>
					</div>
				<% } %>
				</div>
			</section>
		</div>
	</section>

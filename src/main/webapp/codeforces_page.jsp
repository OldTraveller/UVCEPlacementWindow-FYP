<%@page import="POJOS.StudentData"%>
<%@page import="Model.StudentModel"%>
<head>
  <link rel="stylesheet" href="styles/style.css">
  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  <script src="https://code.getmdl.io/1.3.0/material.min.js"></script>
  <script src="js/single.js"></script>
</head>
<%
	StudentData studentData = StudentModel.getDigitalSignaturePresentStudentData();
%>
<body>
  		  <form action="" id="handleform">
              <input name="handle" class="hidden" value="<%= studentData.getStudentCodeforcesHandle() %>" id="handle">
              <button type="submit" style="width: 100%" class="btn btn-success">Get Statistics</button>
          </form>
          
        <div class="container">
        	<div class="row">
        	     <div class="col-12 col-sm-6 col-lg-6 col-md-6">
	        	    <div id="verdicts" class="hidden">
			        </div>
        	     </div>
        	     <div class="col-12 col-sm-6 col-lg-6 col-md-6">
			        <div id="langs" class="hidden">
			        </div>
        	     </div>
        	</div>
        </div>
        <hr>
        
	<div class="container">
		<div class="row">
			<div class="col-12 col-sm-12 col-lg-12 col-md-12">
				<div id="ratings" class="hidden"></div>
			</div>
		</div>
	</div>

	<div class="container">
        	<div class="row">
        	     <div class="col-12 col-sm-12 col-lg-12 col-md-12">
			        <div id="levels" class="hidden">
			        </div>
        	     </div>
        	</div>
        </div>
        
     	<div class="container" class="hidden">
        	<div class="row">
	        	<div class="col-12 col-sm-12 col-lg-2 col-md-2">
	        		<!-- EMPTY TAG -->
	        	</div>
	        	<div class="col-12 col-sm-12 col-lg-8 col-md-8" style='border: solid 2px black;'>
			        <div id="tags">
		       		</div>
	       		</div>
	       		<div class="col-12 col-sm-12 col-lg-2 col-md-2">
	       			<!-- EMPTY TAG -->
	        	</div>
       		</div>
       	</div>
       	
       	
       	<div class="container">
        	<div class="row">
	        	<div class="col-12 col-sm-2 col-lg-2 col-md-2">
	        		<!-- EMPTY TAG -->
	        	</div>
	        	<div class="col-12 col-sm-8 col-lg-8 col-md-8">
			        <div id="contests" class="hidden">
			          <table class="table table-hover">
			            <tr>
			              <th>Contests of</th>
			              <th class="handle-text"></th>
			            </tr>
			            <tr>
			              <td>Number of contests</td>
			              <td id="contestCount"></td>
			            </tr>
			            <tr>
			              <td>Best rank</td>
			              <td id="best"></td>
			            </tr>
			            <tr>
			              <td>Worst rank</td>
			              <td id="worst"></td>
			            </tr>
			            <tr>
			              <td>Max up</td>
			              <td id="maxUp"></td>
			            </tr>
			            <tr>
			              <td>Max down</td>
			              <td id="maxDown"></td>
			            </tr>
			          </table>
			        </div>
	       		</div>
	       		<div class="col-12 col-sm-2 col-lg-2 col-md-2">
	       			<!-- EMPTY TAG -->
	        	</div>
       		</div>
       	</div>
       	
        
  <script>
  (function(i, s, o, g, r, a, m) {
    i['GoogleAnalyticsObject'] = r;
    i[r] = i[r] || function() {
      (i[r].q = i[r].q || []).push(arguments)
    }, i[r].l = 1 * new Date();
    a = s.createElement(o),
      m = s.getElementsByTagName(o)[0];
    a.async = 1;
    a.src = g;
    m.parentNode.insertBefore(a, m)
  })(window, document, 'script', 'https://www.google-analytics.com/analytics.js', 'ga');

  ga('create', 'UA-90813960-1', 'auto');
  ga('send', 'pageview');
  ga('send', 'event', 'Form', 'submit');
  </script>
  <script>
  if('serviceWorker' in navigator) {
    navigator.serviceWorker.register('sw.js')
  }
  </script>
  <script async defer src="https://buttons.github.io/buttons.js"></script>
</body>

</html>

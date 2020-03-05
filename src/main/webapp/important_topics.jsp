<%@ include file = "/partials/header.jsp" %>	
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

    <section class="container totn-border w3-animate-right">
        <section class="hero is-black">
            <div class="hero-body" >
                <div class="container" align="center">
                    <h1 ><strong>
                        <span style="color: #003366;">IMPORTANT TOPICS</span>
                    </strong></h1>
                    <h3 class="subtitle"> <span style="color: #003366;">
                    		LIST OF IMPORTANT TOPICS
                    	</span>
                    </h3>
                </div>
            </div>
        </section>
    </section>
    <hr>
    
    <section class="container w3-animate-zoom" align="center">
   		<section class="container w3-animate-zoom" align="center">
	  			<h2><strong>FILTER SUBJECT WISE</strong></h2>
		  		<section class="container" align="center">
		    		<div class="field has-addons has-addons-centered" align="center">
					  <p class="control">
					    <span class="select">
					      <select id="which" >
					        <option value="0">ALL CATEGORIES</option>
					        <option value="1">DATA STRUCTURES</option>
					        <option value="2">ALGORITHMS</option>
					        <option value="3">OPERATING SYSTEMS</option>
					        <option value="4">DATABASE MANAGEMENT SYSTEMS</option>
					        <option value="5">OTHERS</option>
					      </select>
					    </span>
					  </p>
					  	<button class="btn btn-danger" onclick="sendRequestToImportantTopics()">
					      SEARCH
					    </button>
					    <button class="btn btn-info" onclick="clearStuff()">
					      CLEAR TABLE
					    </button>
					</div>
		    	</section>
		    	<hr>
	    </section>
  			<div class="row">
  				    <section class="container">
				    	<div class="row" id="populateHere"></div>
				    </section>
  			</div>
    </section>
    <hr>

<jsp:include page="./partials/footer.jsp" />

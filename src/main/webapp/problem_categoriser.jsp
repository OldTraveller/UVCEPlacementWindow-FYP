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
                        <span style="color: #003366;">Codeforces Problem Categoriser</span>
                    </strong></h1>
                    <h4 align="center">Build on top of Codeforces API</h4>
                </div>
            </div>
        </section>
    </section>
    <hr>
    
    <section class="container w3-animate-zoom" align="center">
   		<section class="container w3-animate-zoom" align="center">
	  			<h3><strong>SELECT RATING</strong></h3>
		  		<section class="container" align="center">
		    		<div class="field has-addons has-addons-centered" align="center">
					  <p class="control">
					    <span class="select">
					      <select id="cf_rating" >
					        <option value="500">500</option>
					        <option value="600">600</option>
					        <option selected value="700">700</option>
					        <option value="800">800</option>
					        <option value="900">900</option>
					        <option value="1000">1000</option>
					        <option value="1100">1100</option>
					        <option value="1200">1200</option>
					        <option value="1300">1300</option>
					        <option value="1400">1400</option>
					        <option value="1500">1500</option>
					        <option value="1600">1600</option>
					        <option value="1700">1700</option>
					        <option value="1800">1800</option>
					        <option value="1900">1900</option>
					        <option value="2000">2000</option>
					        <option value="2100">2100</option>
					        <option value="2200">2200</option>
					        <option value="2300">2300</option>
					        <option value="2400">2400</option>
					        <option value="2500">2500</option>
					        <option value="2600">2600</option>
					        <option value="2700">2700</option>
					        <option value="2800">2800</option>
					        <option value="2900">2900</option>
					        <option value="3000">3000</option>
					        <option value="3100">3100</option>
					        <option value="3200">3200</option>
					        <option value="3300">3300</option>
					        <option value="3400">3400</option>
					        <option value="3500">3500</option>
					      </select>
					    </span>
					  </p>
<!-- 						<p class="control">
					      <select multiple id="tags" style="width: 50em; height: 10em;">
					        <option value="implementation">Implementation</option>
					         <option value="brute force">Brute Force</option>
					          <option value="data structures">Data Structures</option>
					           <option value="dfs and similar">Depth First Search Related</option>
					            <option value="divide and conquer">Graph Algorithms</option>
					             <option value="geometry">Geometry</option>
					              <option value="math">Mathematical</option>
					               <option value="number theory">Number Theory</option>
					                <option value="probabilities">Probability</option>
					        <option value="dp">Dynamic Programming</option>
					        <option value="greedy">Greedy Algorithms</option>
					        <option value="binary search">Binary Search</option>
					        <option value="graphs">Graph Algorithms</option>
					      </select>
					  </p> -->
					  	<button class="btn btn-danger" onclick="sendCodeforcesRequest()">
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

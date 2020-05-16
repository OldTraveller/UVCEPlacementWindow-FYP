package Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import POJOS.CodeforcesProblem;

/* 
 * AUTHOR - Abhishek Rai
 * STATUS - Working Perfect!
 */

public class ProblemRecommender {
	static TreeMap<Long, HashSet<CodeforcesProblem>> problemsByRatings = null; 
	
	private static String sendGetRequestToCodeforcesProblemSetAPI() throws IOException {
		/* Send the REQUEST to the below URL to get all the problems in JSON format */ 
		URL codeforcesProblemAPIUrl = new URL("https://codeforces.com/api/problemset.problems");
		HttpURLConnection con = (HttpURLConnection)codeforcesProblemAPIUrl.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-Type", "application/json; utf-8");
		con.setRequestProperty("Accept", "application/json");
		con.setDoOutput(true);
		
		try(BufferedReader br = new BufferedReader(
			 new InputStreamReader(con.getInputStream(), "utf-8"))) {
			 StringBuilder response = new StringBuilder();
			 String responseLine = null;
			 while ((responseLine = br.readLine()) != null) {
			       response.append(responseLine.trim());
			 }
			 return response.toString();
		}
	}
	
	private static void loadProblemsInTreeMap() throws IOException, ParseException {
		/* GET the JSON response */ 
		String codeforcesProblemApiJSONResponse = sendGetRequestToCodeforcesProblemSetAPI();
		problemsByRatings = new TreeMap<Long, HashSet<CodeforcesProblem>>(); 
		
		/* PARSE the JSON response to extract the fields */ 
		JSONParser parser = new JSONParser();
		JSONObject response = (JSONObject)parser.parse(codeforcesProblemApiJSONResponse);
		JSONObject result = (JSONObject)response.get("result"); 
		JSONArray problems = (JSONArray)result.get("problems"); 
		JSONArray problemStatistics = (JSONArray)result.get("problemStatistics"); 
		
			for (int i = 0; i < problems.size(); i++) {
				/* 
				 * The number of problems solved is present in the same index of problemSolved array 
				 * Therefore, we can just access it by the index number.
				 * problemStatistics[i] will contain the total solveCount for the the problems[i] 
				 * */ 
				JSONObject problem = (JSONObject)problems.get(i); 
				JSONObject problemSolved = (JSONObject)problemStatistics.get(i); 
				long contestId = (problem.get("contestId") != null ? (long)problem.get("contestId") : 0L);  
				String index = (problem.get("index") != null ? problem.get("index").toString() : "");  
				String name = (problem.get("name") != null ? problem.get("name").toString() : "");  
				long rating = (problem.get("rating") != null ? (long)problem.get("rating") : 0L);  
				long totalSolveCount = (problemSolved.get("solvedCount") != null ? (long)problemSolved.get("solvedCount") : 0L);  
				JSONArray tagsJSON = (JSONArray)problem.get("tags"); 
				HashSet<String> tags = new HashSet<String>(); 
				for (int j = 0; j < tagsJSON.size(); j++) {
					tags.add(tagsJSON.get(j).toString());
				}
				CodeforcesProblem newCodeforcesProblem = new CodeforcesProblem(contestId, index, name, rating, tags, totalSolveCount);
				
				if (!problemsByRatings.containsKey(rating)) {
					problemsByRatings.put(rating, new HashSet<CodeforcesProblem>());
				}
				/* Put the problem according to the rating */ 
				problemsByRatings.get(rating).add(newCodeforcesProblem);
		}
	}
	
	public static HashSet<CodeforcesProblem> getProblemsByRatingFromTree(long rating) throws IOException, ParseException {
		// Design Pattern. 
		if (problemsByRatings == null) {
			/* LOAD the PROBLEMS into the RAM */ 
			System.out.println("Hitting the Codeforces Server"); 
			loadProblemsInTreeMap(); 
		}
		return problemsByRatings.get(rating); 
	}
	
	public static HashSet<CodeforcesProblem> getProblemsByCategoryForGivenRating(long rating, ArrayList<String> tags) throws IOException, ParseException {
		HashSet<CodeforcesProblem> categoryProblemsForRating = new HashSet<CodeforcesProblem>();
		if (problemsByRatings == null) {
			/* LOAD the PROBLEMS into the RAM */ 
			System.out.println("Hitting the Codeforces Server"); 
			loadProblemsInTreeMap(); 
		}
		HashSet<CodeforcesProblem> getByRating = getProblemsByRatingFromTree(rating); 
		System.out.println("The size of getByRating is : " + getByRating.size()); 
		if (getByRating != null) {
			for (CodeforcesProblem problem : getByRating) {
				if (problem.getTags().containsAll(tags)) {
					categoryProblemsForRating.add(problem);
				}
			}
		}
		return categoryProblemsForRating;
	}

	/* TESTING API */ 
	public static void main(String[] args) throws IOException, ParseException {
		Scanner scanner = new Scanner(System.in); 

		while(true) {
			System.out.println("Enter the rating : "); 
			long rating = scanner.nextLong(); 
			
			ArrayList<String> tags = new ArrayList<String>();
			tags.add("implementation"); 
			HashSet<CodeforcesProblem> getByRatingWithCategory = getProblemsByCategoryForGivenRating(rating, tags); 
			
			for (CodeforcesProblem problem : getByRatingWithCategory) {
				System.out.println(); 
				System.out.println("NAME : " + problem.getName()); 
				System.out.println("TAGS : "); 
				for (String tag : problem.getTags()) {
					System.out.print(tag + ", ");
				}
				System.out.print("\n--------------------------------------------");
			}
		}
	}

}

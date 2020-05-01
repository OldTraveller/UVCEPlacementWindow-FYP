package ProblemRecommender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ProblemRecommender {

	private static String sendGetRequestToCodeforcesProblemSetAPI(ArrayList<String> problemTags) throws IOException {
		StringBuilder url = new StringBuilder("https://codeforces.com/api/problemset.problems?tags=");
		/* 
		 * FRAME THE URL WITH TAGS 
		 * For multiple tags we frame the URL like this -
		 * https://codeforces.com/api/problemset.problems?tags=dp;greedy;graphs;
		 * These tags are separated by semicolon(;) 
		 * 
		 * */ 
		for (String tag : problemTags) {
			url.append(tag); 
			url.append(";"); 
		}
		URL codeforcesProblemAPIUrl = new URL(url.toString());
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
			 System.out.println(response.toString());
			 return response.toString();
		}
	}
	
	public static void main(String[] args) throws IOException {
		ArrayList<String> problemTags = new ArrayList<String>(); 
		problemTags.add("greedy"); 
		problemTags.add("binary search"); 
		String response = sendGetRequestToCodeforcesProblemSetAPI(problemTags); 
		System.out.println(response);
	}

}

package Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import POJOS.GithubRepository;
import POJOS.StudentData;

public class GithubAPI {
	
	/* EXPERIMENTAL STAGE */ 
	private static String sendGetRequestToGithubAPI(StudentData studentData) throws IOException {
		URL url = new URL ("https://api.github.com/users/" + studentData.getStudentGithubHandle() + "/repos");
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
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
	
	public static ArrayList<GithubRepository> getGithubRepositoriesOfUser(StudentData studentData) throws IOException, ParseException {
		// Need to populate this list with the code. 
		ArrayList<GithubRepository> repositoryList = new ArrayList<GithubRepository>();
		// Get the JSON Reponse from API -> https://api.github.com/users/{GithubHandle}/repos
		String apiResponse = sendGetRequestToGithubAPI(studentData); 
		/* 
		 * Now we have the JSON string in apiResponse Variable. 
		 * -> Parse the JSON String to extract the parameters that we require. 
		 */
		JSONParser parser = new JSONParser();
		// Parse the response using JSONParser. 
        JSONArray array = (JSONArray)parser.parse(apiResponse);
        
        /* Now we have the array of repositories */
        for (int i = 0; i < array.size(); i++) {
        	JSONObject repository = (JSONObject)array.get(i);
        	GithubRepository repositoryObject = new GithubRepository(); 
        	repositoryObject.setRepositoryFullName(repository.get("full_name").toString());
        	repositoryObject.setRepositoryCreatedAt(repository.get("created_at").toString());
        	if (repository.get("description") != null) {
        		repositoryObject.setRepositoryDescription(repository.get("description").toString());
        	}
        	repositoryObject.setRepositoryFurtherInformation(repository.get("url").toString());
        	repositoryObject.setRepositoryHTMLUrl(repository.get("html_url").toString());
        	repositoryObject.setRepositoryPushedAt(repository.get("pushed_at").toString());
        	repositoryObject.setRepositoryUpdatedAt(repository.get("updated_at").toString()); 
        	
        	// Now push the object to the ArrayList.
        	repositoryList.add(repositoryObject);
        }
		return repositoryList;
	}
	
	/* API Test Run 
	 * Status - Working Perfect!
	 * */ 
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException, ParseException {
		StudentData studentData = new StudentData();
		studentData.setStudentGithubHandle("OldTraveller");
		ArrayList<GithubRepository> response = getGithubRepositoriesOfUser(studentData);
		for (GithubRepository repository : response) {
			System.out.println(repository);
		}
	}
}

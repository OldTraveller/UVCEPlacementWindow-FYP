package Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class SpamDetectionModel {
	
	/**
	 * FUNCTION which sends a POST request to the Python API created in Flask. 
	 * @param text - The text that needs to be checked for SPAM. 
	 * @return responseString - Contains the JSON response body of the POST request's response. 
	 * @throws IOException
	 */
	private static String sendPostRequest(final String text) throws IOException {
		String responseString = null; 
		/* URL to the Python SpamFilter API */ 
		URL url = new URL ("http://127.0.0.1:5000/checkSpamFilter");
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json; utf-8");
		con.setRequestProperty("Accept", "application/json");
		con.setDoOutput(true);
		String jsonInputString = "{\"text_to_be_classified\" : \"" + text + "\"}";
		
		try(OutputStream os = con.getOutputStream()) {
		    byte[] input = jsonInputString.getBytes("utf-8");
		    os.write(input, 0, input.length);           
		}
		
		try(BufferedReader br = new BufferedReader(
			 new InputStreamReader(con.getInputStream(), "utf-8"))) {
			 StringBuilder response = new StringBuilder();
			 String responseLine = null;
			 while ((responseLine = br.readLine()) != null) {
			       response.append(responseLine.trim());
			 }
	         responseString = response.toString(); 
		}
		return responseString; 
	}
	
	/*
	 * Sample Response from the PYTHON API : 
	 * {
		  "email_class": "spam", 
		  "email_text": "hasd lksad fklasd fasdf asdf asd fasd fasd fads .", 
		  "status": 200
		}
	 */
	static String getSpamStatusFromPythonAPI(final String text) throws Exception {
		String responseBody = sendPostRequest(text);
		System.out.println(responseBody);
		JsonParser parser = new JsonParser();
		JsonObject o = parser.parse(responseBody).getAsJsonObject();
		String spamOrNot = o.get("email_class").getAsString(); 
		System.out.println("EMAIL CLASS IN getSpamStatusFromPythonAPI() : " + spamOrNot);
		return spamOrNot; 
	}

	static boolean isSpam(final String text) throws Exception {
		String jsonResponseFromPythonAPI = getSpamStatusFromPythonAPI(text); 
		boolean isSpam = jsonResponseFromPythonAPI.equals("spam"); // or it will be "ham" for non-spam. 
		return isSpam;
	}
	
	/* Can check for different messages as a console based application */ 
	public static void main(String args[]) throws Exception {
		Scanner scanner = new Scanner(System.in); 
		System.out.println("ENTER THE STRING : "); 
		String input = scanner.nextLine();
		try {
			if (isSpam(input)) {
				System.out.println("MESSAGE IS SPAM!");
			} else {
				System.out.println("MESSAGE IS NOT A SPAM!");
			}
		} catch (Exception e) {
			System.out.println("THE API REFUSED CONNECTION!");
		}
		scanner.close();
		System.out.println(System.currentTimeMillis());
	}
}

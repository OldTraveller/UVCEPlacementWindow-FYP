package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApplicationConstants {
	// "D:\\PrivateKeyUser.txt"
	static final String DIGITAL_SIGNATURE_PATH = "D:\\PrivateKeyUser.txt";
	static final String DIGITAL_SIGNATURE_PATH_NEW_USER = "E:\\PrivateKeyUser.txt"; 
	static final String DIGITAL_SIGNATURE_PATH1 = "C:\\Users\\I532620\\eclipse-workspace\\PlacementWindow\\src\\main\\java\\Model\\PrivateKeyUser.txt";	
	public static ArrayList<String> getStatusMessages() {
		List<String> list = Arrays.asList(
				"Your post might be reflecting now!", 
				"Your post is put in review Queue! Hope to push it soon.", 
				"A valid Digital Signature Device (DSD) might not be present! Make sure your signature device is properly plugged in",
				"The user registration is successfull!!"
		);
		ArrayList<String> messages = new ArrayList<String>(list); 
		return messages; 
	}
}

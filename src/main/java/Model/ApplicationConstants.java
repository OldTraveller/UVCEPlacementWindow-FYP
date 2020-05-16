package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApplicationConstants {
	// "D:\\PrivateKeyUser.txt"
	static final String DIGITAL_SIGNATURE_PATH = "H:\\PrivateKeyUser.txt";
	static final String DIGITAL_SIGNATURE_PATH_NEW_USER = "I:\\PrivateKeyUser.txt"; 
	static final String DIGITAL_SIGNATURE_PATH_USER = "I:\\PrivateKeyUser.txt"; 
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
	
	public static String getDigitalSignaturePath() {
		String path = DIGITAL_SIGNATURE_PATH; 
		
		File userFile = new File(DIGITAL_SIGNATURE_PATH_USER); 
		File masterFile = new File(DIGITAL_SIGNATURE_PATH);
		
		boolean masterFilePresent = masterFile.exists();
		boolean userFilePresent = userFile.exists();
		
		if (masterFilePresent && userFilePresent) {
			path = DIGITAL_SIGNATURE_PATH;
		} else if (masterFilePresent) {
			path = DIGITAL_SIGNATURE_PATH; 
		} else if (userFilePresent) {
			path = DIGITAL_SIGNATURE_PATH_USER;
		}
		
		return path;
	}
}

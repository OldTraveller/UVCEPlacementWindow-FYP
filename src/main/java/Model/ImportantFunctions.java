package Model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ImportantFunctions {
	
	/* Function to get the SHA-1 Hash of some given string */ 
	public static String getSHAString (String password) throws NoSuchAlgorithmException {
		MessageDigest mDigest = MessageDigest.getInstance("SHA1");
	    byte[] result = mDigest.digest(password.getBytes());

	    /* CAN USE STRING BUILDER ALSO */
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < result.length; i++) {
	        sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
	    }
	    /* My modification to SHA Hash */ 
	    sb.setCharAt(21, 'a');
	    return sb.toString();
	}

}

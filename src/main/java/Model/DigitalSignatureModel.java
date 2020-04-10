package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import POJOS.StudentData;

public class DigitalSignatureModel {
	
	static boolean generateNewDigitalSignatureFile(StudentData studentData) throws IOException {
		boolean isFileGenerated = true; 
		FileWriter file = new FileWriter(ApplicationConstants.DIGITAL_SIGNATURE_PATH); 
		String privateKey = studentData.getStudentPrivateKey(); 
		System.out.println("The private key in generateNewDigitalSignatureFile() is : " + privateKey);
		try {
			file.write(privateKey);
		} catch (Exception e) {
			System.out.println("ERROR OCCURED WHILE BURNING DIGITAL SIGNATURE TO THE PENDRIVE.");
			isFileGenerated = false; 
		} finally {
			file.close();
		}
		return isFileGenerated;
	}
	
	public static boolean isDigitalSignaturePresent() throws ClassNotFoundException, URISyntaxException, SQLException, FileNotFoundException {
		boolean isValid = true;
		/* Defined like this - 
		 * static final String DIGITAL_SIGNATURE_PATH = "D:\\PrivateKeyUser.txt";
		 */
		File file = new File(ApplicationConstants.DIGITAL_SIGNATURE_PATH);
		
		/* 
		 * I will first check whether some file exists with this file URL.
		 * if does not exists we can say that Digital Signature is not there. Since the file that we are 
		 * looking for is not present itself. So isValid = false, should be returned. 
		 * */ 
		if (!file.exists()) {
			isValid = false; 
			return isValid; 
		}
		/* 
		 * Return statement is made in the block before itself, because we do not need to make a Database
		 * Query in case we do not find such a file itself. 
		 * In case such a file is present, we read the value of the PrivateKey from the file and then 
		 * compare it with the details that is stored in our Database.
		 * Then based on the result from the database, 
		 * if (USER PRIVATE KEY is PRESENT in DATABASE):
		 * 		AUTHENTICATE
		 * else: 
		 * 		RESTRICT POSTING 
		 */
		boolean checkDigitalSignatureFileAndCompare = getCheckDigitalSignatureFileAndCompare(); 
		if (!checkDigitalSignatureFileAndCompare) {
			isValid = false; 
		}
		
		return isValid; 
	}
	
	private static boolean getCheckDigitalSignatureFileAndCompare() throws ClassNotFoundException, URISyntaxException, SQLException, FileNotFoundException {
		boolean isDigitalSignatureValid = true; 
		/* Get the private key from the File */ 
		String privateKey = null; 
		File filePath = new File(ApplicationConstants.DIGITAL_SIGNATURE_PATH); 
		Scanner scanner = new Scanner(filePath); 
		while (scanner.hasNextLine()) {
			privateKey = scanner.nextLine();
		}
		if (privateKey == null) {
			isDigitalSignatureValid = false; 
		} else {
			/* Establish a connection and check if the privateKey is present in DB */ 
			Connection connection = DatabaseConnection.getLocalConnection(); 
			Statement statement = connection.createStatement(); 
			String sql = "SELECT COUNT(*) FROM STUDENT_DATA WHERE STUDENT_PRIVATE_KEY = '" + privateKey + "' AND STUDENT_ISVALID = 1";
			ResultSet resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				isDigitalSignatureValid = (resultSet.getInt(1) == 0) ? false : true; 
			}
			statement.close();
			connection.close();
		}
		scanner.close();
		return isDigitalSignatureValid;
	}
	
	public static String getPrivateKeyFromFile() throws FileNotFoundException {
		String privateKey = null; 
		File filePath = new File(ApplicationConstants.DIGITAL_SIGNATURE_PATH); 
		Scanner scanner = new Scanner(filePath); 
		while (scanner.hasNextLine()) {
			privateKey = scanner.nextLine();
		}
		scanner.close();
		return privateKey;
	}

	public static void main(String[] args) throws ClassNotFoundException, FileNotFoundException, URISyntaxException, SQLException {
		if (isDigitalSignaturePresent()) {
			System.out.println("DIGITAL SIGNATURE IS PRESENT!!!");
		} else {
			System.out.println("DIGITAL SIGNATURE IS NOT PRESENT!!");
		}
	}
}

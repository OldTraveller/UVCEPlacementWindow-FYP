package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import POJOS.StudentData;

public class StudentModel {
	
	/**
	 * 	PURPOSE - Function to insert a Student POJO to the Database. 
	 *  RETURNS - true or false, if the record is inserted into the database or not.
	 *  */ 
	public static boolean insertStudentRecord(StudentData studentData) throws Exception {
		boolean isInserted = false; 
		/* Connection Establishment */
		Connection connection = DatabaseConnection.getLocalConnection(); 
		String sql = "INSERT INTO STUDENT_DATA VALUES (?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(sql); 
		try {
			/* Setting all the parameters */ 
			ps.setString(1, studentData.getStudentUSN());
			ps.setString(2, studentData.getStudentName());
			ps.setString(3, studentData.getStudentBranch()); 
			ps.setInt(4, studentData.getStudentSemester());
			/* Setting of PUBLIC and PRIVATE key */ 
			ps.setString(5, studentData.getStudentPublicKey()); 
			ps.setString(6, studentData.getStudentPrivateKey()); 
			ps.setString(7, studentData.getStudentEmail()); 
			/* Initially setting all the students to be INVALID - 0 */ 
			ps.setInt(8, studentData.getStudentIsValid());
			ps.setString(9, studentData.getStudentCodeforcesHandle());
			ps.setString(10, studentData.getStudentGithubHandle());
			isInserted = (ps.executeUpdate() > 0);
		} catch (Exception e) {
			System.out.println("There was a problem in INSERTING STUDENT RECORD."); 
			e.printStackTrace();
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (connection != null) {
				connection.close(); 
			}
		}
		return isInserted;
	}
	
	/* 
	 * This function makes a given STUDENT POJO class an admin or not. 
	 * If at all the admin checkbox is selected in the main window. Then this function is executed. 
	 * Otherwise it won't be executed.
	 * The function makes new entries to the ADMIN_DATA table. 
	 */
	public static void makeAdmin(StudentData studentData) throws SQLException, ClassNotFoundException, URISyntaxException {
		/* Connection Establishment */
		Connection connection = DatabaseConnection.getLocalConnection(); 
		String sql = "INSERT INTO ADMIN_DATA VALUES (?,?,?)";
		PreparedStatement ps = connection.prepareStatement(sql); 
		try {
			/* Setting all the parameters */ 
			ps.setString(1, studentData.getStudentUSN());
			ps.setString(2, studentData.getStudentPublicKey()); 
			ps.setString(3, studentData.getStudentPrivateKey()); 
			ps.executeUpdate(); 
		} catch (Exception e) {
			System.out.println("There was a problem in INSERTING STUDENT RECORD."); 
			e.printStackTrace();
		} finally {
			System.out.println("The record was inserted in the ADMIN table!"); 
			if (ps != null) {
				ps.close();
			}
			if (connection != null) {
				connection.close(); 
			}
		}
	}
	
	/* RETURNS if the DigitalSignature is ADMIN or not */ 
	public static boolean isAdmin() throws FileNotFoundException, ClassNotFoundException, URISyntaxException, SQLException {
		Connection connection = DatabaseConnection.getLocalConnection(); 
		Statement statement = connection.createStatement(); 
		String sql = "SELECT * FROM ADMIN_DATA WHERE STUDENT_PRIVATE_KEY = '" + DigitalSignatureModel.getPrivateKeyFromFile() + "'"; 
		ResultSet result = statement.executeQuery(sql); 
		boolean isAdminUser = result.next(); 
		statement.close(); 
		connection.close();
		return isAdminUser;
	}

	public static StudentData getDigitalSignaturePresentStudentData() throws FileNotFoundException, SQLException, ClassNotFoundException, URISyntaxException, NoSuchAlgorithmException {
		String privateKey = null; 
		File filePath = new File(ApplicationConstants.getDigitalSignaturePath()); 
		Scanner scanner = new Scanner(filePath); 
		while (scanner.hasNextLine()) {
			privateKey = scanner.nextLine();
		}
		Connection connection = DatabaseConnection.getLocalConnection(); 
		Statement statement = connection.createStatement(); 
		String sql = "SELECT * FROM STUDENT_DATA WHERE STUDENT_PRIVATE_KEY = '" + privateKey + "'"; 
		ResultSet result = statement.executeQuery(sql); 
		
		StudentData studentData = new StudentData();
		while (result.next()) {
			studentData.setStudentUSN(result.getString(1)); 
			studentData.setStudentName(result.getString(2)); 
			studentData.setStudentBranch(result.getString(3));
			studentData.setStudentSemester(result.getInt(4)); 
			studentData.setStudentPublicKey(result.getString(5));
			studentData.setStudentPrivateKey(result.getString(6)); 
			studentData.setStudentEmail(result.getString(7));
			studentData.setStudentIsValid(result.getInt(8));
			studentData.setStudentCodeforcesHandle(result.getString(9));
			studentData.setStudentGithubHandle(result.getString(10));
		}
		scanner.close();
		statement.close();
		connection.close();
		return studentData; 
	}
	
	public static void main(String[] args) throws Exception {
		StudentData studentData = new StudentData(); 
		studentData.setStudentBranch("CSE");
		studentData.setStudentEmail("abhishekrai19999@gmail.com");
		studentData.setStudentIsValid(1);
		studentData.setStudentName("Abhishek Kumar Rai");
		studentData.setStudentSemester(8);
		studentData.setStudentUSN("16GAEC9002");
		
		try {
			boolean isWritten = DigitalSignatureModel.generateNewDigitalSignatureFile(studentData); 
			if (isWritten) {
				boolean isStudentInserted = insertStudentRecord(studentData);
				if (isStudentInserted) {
					System.out.println("Digital Signature Written to Pendrive.");
				}
			} else {
				System.out.println("Some error occured during Digital Signature burning.");
			}
		} catch (Exception e) {
			System.out.println("Some error occured during Digital Signature burning.");
			e.printStackTrace();
		}
	}
}

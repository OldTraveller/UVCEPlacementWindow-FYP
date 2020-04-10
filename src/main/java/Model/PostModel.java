package Model;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import POJOS.Post;

public class PostModel {
	
	/**
	 * @param post - Object of type POST to be inserted to the DATABASE. 
	 * @return - boolean value indicating whether the insert of POST was successfull or not. 
	 * @throws Exception 
	 */
	public static boolean insertPostRecord(Post post) throws Exception {
		boolean isInserted = false; 
		/* Connection Establishment */
		Connection connection = DatabaseConnection.getLocalConnection(); 
		String sql = "INSERT INTO POSTS VALUES (?,?,?,?,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(sql); 
		
		int spamCheckResult = 1; // By default categorise it as SPAM.
		try {
			spamCheckResult = getSpamCheckResults(post); 
		} catch (Exception e) {
			System.out.println("Spam Status remains the same as before, since cannot connect to SPAM CHECKER");
		}
		
		try {
			System.out.println(post.getPostId());
			/* Setting all the parameters */ 
			ps.setLong(1, post.getPostId());
			ps.setString(2, post.getPostName());
			ps.setString(3, post.getPostDesc());
			ps.setString(4, post.getPostLink());
			ps.setInt(5, post.getPostSubjectId());
			/* Note here we have called the SPAM Detector API */ 
			ps.setInt(6, spamCheckResult);
			ps.setString(7, post.getStudentUsn());
			isInserted = (ps.executeUpdate() > 0);
		} catch (Exception e) {
			System.out.println("There was a problem in INSERTING POST RECORD."); 
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
	
	/**
	 * @param post - The POST object that needs to be edited. 
	 * @return - boolean value indicating whether the POST edit was successfull or not. 
	 * @throws Exception
	 */
	public static boolean editPostRecord(Post post) throws Exception {
		boolean isUpdated = false; 
		/* Connection Establishment */
		Connection connection = DatabaseConnection.getLocalConnection(); 
		int spamCheckResult = (post.isPostIsSpam() ? 1 : 0); 
		try {
			spamCheckResult = getSpamCheckResults(post); 
		} catch (Exception e) {
			System.out.println("Spam Status remains the same as before, since cannot connect to SPAM CHECKER");
		}
		
		String sql = "UPDATE POSTS SET POST_NAME = '" + post.getPostName() + "',"
				+ " POST_DESC = '" + post.getPostDesc() + "', " 
				+ " POST_LINK = '" + post.getPostLink() + "', " 
				+ " POST_SUBJECT_ID = " + post.getPostSubjectId() + ", " 
				+ " POST_ISSPAM = " + spamCheckResult 
				+ " WHERE POST_ID = " + post.getPostId();
		
		System.out.println("UPDATE QUERY : " + sql); 
		PreparedStatement ps = connection.prepareStatement(sql); 
		try {
			/* Note here we have called the SPAM Detector API */ 
			isUpdated = (ps.executeUpdate() > 0);
		} catch (Exception e) {
			System.out.println("There was a problem in INSERTING POST RECORD."); 
			e.printStackTrace();
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (connection != null) {
				connection.close(); 
			}
		}
		return isUpdated;
	}
	
	
	
	/*
	 * Returns 1 if the message is a SPAM. 
	 * Returns 0 if the message is not a SPAM. 
	 */
	static int getSpamCheckResults(Post post) throws Exception {
		int spamCheckResults = 0; 
		String contentOverall = post.getPostDesc(); 
		/* Here it checks if content is SPAM or NOT */ 
		boolean checkSpam = SpamDetectionModel.isSpam(contentOverall); 
		if (checkSpam) {
			spamCheckResults = 1; 
		}
		return spamCheckResults; 
	}
	
	/*
	 * Returns the ArrayList of all the subjects with the SUBJECT_ID and SUBJECT_NAME 
	 * pairs is stored in DB. 
	 * 
	 * 1 - DATA STRUCTURES
	 * 2 - ALGORITHMS
	 * 3 - OPERATING SYSTEMS
	 * 4 - DBMS
	 * 5 - OTHERS
	 * 
	 * Since we get the numbers as 1 based indexing, I have put a dummy variable called "ALL CATEGORIES" 
	 * at index 0. So, we can access the names of the subjects with just the numbers. without shifting it 
	 * by -1 every time in every function call. 
	 */
	public static ArrayList<String> getSubjectNamesArrayList() {
		List<String> list = Arrays.asList("ALL CATEGORIES", 
										  "DATA STRUCTURES", 
										  "ALGORITHMS", 
										  "OPERATING SYSTEMS", 
										  "DBMS", 
										  "OTHERS");
		ArrayList<String> subjects = new ArrayList<String>(list); 
		return subjects; 
	}
	
	/**
	 * @return - An ArrayList of all the POSTs made by the person whose Digital Signature Device is 
	 * 			 logged in. 
	 * @throws ClassNotFoundException
	 * @throws URISyntaxException
	 * @throws SQLException
	 * @throws FileNotFoundException
	 * @throws NoSuchAlgorithmException
	 */
	public static ArrayList<Post> getAllPostsFromDatabaseForUSN() throws ClassNotFoundException, URISyntaxException, SQLException, FileNotFoundException, NoSuchAlgorithmException {
		ArrayList<Post> posts = new ArrayList<Post>();
		Connection connection = DatabaseConnection.getLocalConnection(); 
		
		String digitalSignUSN = StudentModel.getDigitalSignaturePresentStudentData().getStudentUSN(); 
		String sql = "SELECT * FROM POSTS WHERE STUDENT_USN = '" + digitalSignUSN + "'"; 
		Statement statement = connection.createStatement(); 
		ResultSet resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			Post post = new Post(); 
			post.setPostId(resultSet.getLong(1));
			post.setPostName(resultSet.getString(2));
			post.setPostDesc(resultSet.getString(3));
			post.setPostLink(resultSet.getString(4));
			post.setPostSubjectId(resultSet.getInt(5));
			post.setPostIsSpam((resultSet.getInt(6) > 0) ? true : false);
			post.setStudentUsn(digitalSignUSN);
			posts.add(post); 
		}
		
		statement.close(); 
		connection.close();
		return posts; 
	}
	
	/**
	 * @return - An ArrayList of all the POSTs from the POSTS Table. 
	 * @throws ClassNotFoundException
	 * @throws URISyntaxException
	 * @throws SQLException
	 * @throws FileNotFoundException
	 * @throws NoSuchAlgorithmException
	 */
	public static ArrayList<Post> getAllPostsFromDatabase() throws ClassNotFoundException, URISyntaxException, SQLException, FileNotFoundException, NoSuchAlgorithmException {
		ArrayList<Post> posts = new ArrayList<Post>();
		Connection connection = DatabaseConnection.getLocalConnection(); 
		
		String digitalSignUSN = StudentModel.getDigitalSignaturePresentStudentData().getStudentUSN(); 
		String sql = "SELECT * FROM POSTS"; 
		Statement statement = connection.createStatement(); 
		ResultSet resultSet = statement.executeQuery(sql);
		while (resultSet.next()) {
			Post post = new Post(); 
			post.setPostId(resultSet.getLong(1));
			post.setPostName(resultSet.getString(2));
			post.setPostDesc(resultSet.getString(3));
			post.setPostLink(resultSet.getString(4));
			post.setPostSubjectId(resultSet.getInt(5));
			post.setPostIsSpam((resultSet.getInt(6) > 0) ? true : false);
			post.setStudentUsn(digitalSignUSN);
			posts.add(post); 
		}
		statement.close(); 
		connection.close();
		return posts; 
	}
	
	/**
	 * @param postId
	 * @return a POST with the given postId
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws NoSuchAlgorithmException
	 * @throws SQLException
	 * @throws URISyntaxException
	 */
	public static Post getPostWithPostId(String postId) throws FileNotFoundException, ClassNotFoundException, NoSuchAlgorithmException, SQLException, URISyntaxException {
		Post post = new Post(); 
		Connection connection = DatabaseConnection.getLocalConnection(); 
		String digitalSignUSN = StudentModel.getDigitalSignaturePresentStudentData().getStudentUSN(); 
		String sql = "SELECT * FROM POSTS WHERE STUDENT_USN = '" + digitalSignUSN + "' AND POST_ID = " + postId; 
		Statement statement = connection.createStatement(); 
		ResultSet resultSet = statement.executeQuery(sql);
		while (resultSet.next()) {
			post.setPostId(resultSet.getLong(1));
			post.setPostName(resultSet.getString(2));
			post.setPostDesc(resultSet.getString(3));
			post.setPostLink(resultSet.getString(4));
			post.setPostSubjectId(resultSet.getInt(5));
			post.setPostIsSpam((resultSet.getInt(6) > 0) ? true : false);
			post.setStudentUsn(digitalSignUSN);
		}
		statement.close(); 
		connection.close();
		return post; 
	}
	
	/**
	 * Toggles the state of the SPAM Posts to NON SPAM and vice versa. 
	 * @param postId
	 * @throws ClassNotFoundException
	 * @throws URISyntaxException
	 * @throws SQLException
	 */
	public static void toggleSpamStatusForPost(String postId) throws ClassNotFoundException, URISyntaxException, SQLException {
		String sql = "UPDATE POSTS SET POST_ISSPAM = MOD(POST_ISSPAM + 1, 2) WHERE POST_ID = " + postId; 
		Connection connection = DatabaseConnection.getLocalConnection(); 
		Statement statement = connection.createStatement(); 
		statement.executeUpdate(sql); 
		statement.close(); 
		connection.close();
	}
	
	/* TEST Program for the POST model */ 
	public static void main(String[] args) throws Exception {
		Post post = new Post();
		post.setPostDesc("The problem is opposite of this post. We are given a stack data structure with push and pop operations, the task is to implement a queue using instances of stack data structure and operations on them.");
		post.setPostLink("https://google.com");
		post.setPostSubjectId(2);
		post.setStudentUsn("16GAEC9002");
		post.setPostName("This is regarding Binary Search Trees");
		
		if (insertPostRecord(post)) {
			System.out.println("RECORD INSERTED"); 
		} else {
			System.out.println("UNSUCCESSFULL!"); 
		}
	}
}

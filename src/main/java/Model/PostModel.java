package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import POJOS.Post;

public class PostModel {
	static boolean insertPostRecord(Post post) throws Exception {
		boolean isInserted = false; 
		/* Connection Establishment */
		Connection connection = DatabaseConnection.getLocalConnection(); 
		String sql = "INSERT INTO POSTS VALUES (?,?,?,?,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(sql); 
		
		try {
			System.out.println(post.getPostId());
			/* Setting all the parameters */ 
			ps.setLong(1, post.getPostId());
			ps.setString(2, post.getPostName());
			ps.setString(3, post.getPostDesc());
			ps.setString(4, post.getPostLink());
			ps.setInt(5, post.getPostSubjectId());
			/* Note here we have called the SPAM Detector API */ 
			ps.setInt(6, getSpamCheckResults(post));
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

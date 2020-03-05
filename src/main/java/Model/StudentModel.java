package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import POJOS.StudentData;

public class StudentModel {
	
	static boolean insertStudentRecord(StudentData studentData) throws Exception {
		boolean isInserted = false; 
		/* Connection Establishment */
		Connection connection = DatabaseConnection.getLocalConnection(); 
		String sql = "INSERT INTO STUDENT_DATA VALUES (?,?,?,?,?,?,?,?)";
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
			ps.setInt(8, 0);
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

	
}

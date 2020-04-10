package Controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.DigitalSignatureModel;
import Model.StudentModel;
import POJOS.StudentData;

public class CreateNewUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CreateNewUser() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("CreateNewUser Servlet");
		
		/*
		 * Get all the parameters 
		 */
		String studentUSN = request.getParameter("student_usn"); 
		String studentName = request.getParameter("student_name"); 
		String studentEmail = request.getParameter("student_email"); 
		String studentBranch = request.getParameter("student_branch"); 
		String studentSemester = request.getParameter("student_semester");
		String studentType = request.getParameter("student_type");
		String studentCodeforcesHandle = request.getParameter("student_codeforces_handle"); 
		try {
			StudentData studentData = new StudentData();
			studentData.setStudentUSN(studentUSN);
			studentData.setStudentBranch(studentBranch);
			studentData.setStudentName(studentName);
			studentData.setStudentEmail(studentEmail);
			studentData.setStudentSemester(Integer.parseInt(studentSemester));
			studentData.setStudentIsValid(1);
			studentData.setStudentCodeforcesHandle(studentCodeforcesHandle);
			/*--------------------------------------------------------------------*/
			/*
			 * 1. Insert a normal record to the STUDENT_DATA table. 
			 * 2. Generate a Digital Signature to the Pendrive that is present. 
			 * 3. If the studentType field = "admin", then make one more insertion to
			 * 	  the ADMIN_DATA table. 
			 */
			/*--------------------------------------------------------------------*/
			StudentModel.insertStudentRecord(studentData); 
			DigitalSignatureModel.generateNewDigitalSignatureFile(studentData);
			if (studentType.equals("admin")) {
				StudentModel.makeAdmin(studentData);
			}
			/*--------------------------------------------------------------------*/
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		request.getServletContext().setAttribute("STATUS", 3);
		request.getRequestDispatcher("AdminView").forward(request, response);
	}
}

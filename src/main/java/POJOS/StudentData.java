package POJOS;

import java.security.NoSuchAlgorithmException;

import Model.ImportantFunctions;

/**
 * @author I532620
 */
public class StudentData {
	String studentUSN; 
	String studentName; 
	String studentBranch; 
	int studentSemester;
	String studentPublicKey; 
	String studentPrivateKey; 
	String studentEmail;
	int studentIsValid; 

	public StudentData() throws NoSuchAlgorithmException {
		this.studentPrivateKey = ImportantFunctions.getSHAString(String.valueOf(System.nanoTime()));
		this.studentPublicKey = ImportantFunctions.getSHAString(String.valueOf(System.currentTimeMillis()));
	}

	public String getStudentUSN() {
		return studentUSN;
	}
	public void setStudentUSN(String studentUSN) {
		this.studentUSN = studentUSN;
	}
	public int getStudentIsValid() {
		return studentIsValid;
	}

	public void setStudentIsValid(int studentIsValid) {
		this.studentIsValid = studentIsValid;
	}

	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentBranch() {
		return studentBranch;
	}
	public void setStudentBranch(String studentBranch) {
		this.studentBranch = studentBranch;
	}
	public int getStudentSemester() {
		return studentSemester;
	}
	public void setStudentSemester(int studentSemester) {
		this.studentSemester = studentSemester;
	}
	public String getStudentPublicKey() {
		return studentPublicKey;
	}
	public String getStudentPrivateKey() {
		return studentPrivateKey;
	}
	public String getStudentEmail() {
		return studentEmail;
	}
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	} 
}
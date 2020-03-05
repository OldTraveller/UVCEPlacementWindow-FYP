package POJOS;

public class Post {
	long postId =  System.currentTimeMillis(); 
	String postName; 
	String postDesc; 
	String postLink; 
	int postSubjectId; 
	boolean postIsSpam; 
	String studentUsn;
	
	public boolean isPostIsSpam() {
		return postIsSpam;
	}
	public void setPostIsSpam(boolean postIsSpam) {
		this.postIsSpam = postIsSpam;
	}
	public long getPostId() {
		return postId;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	public String getPostDesc() {
		return postDesc;
	}
	public void setPostDesc(String postDesc) {
		this.postDesc = postDesc;
	}
	public String getPostLink() {
		return postLink;
	}
	public void setPostLink(String postLink) {
		this.postLink = postLink;
	}
	public int getPostSubjectId() {
		return postSubjectId;
	}
	public void setPostSubjectId(int postSubjectId) {
		this.postSubjectId = postSubjectId;
	}
	public String getStudentUsn() {
		return studentUsn;
	}
	public void setStudentUsn(String studentUsn) {
		this.studentUsn = studentUsn;
	} 
}

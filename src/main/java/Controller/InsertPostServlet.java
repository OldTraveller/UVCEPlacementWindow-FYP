package Controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.DigitalSignatureModel;
import Model.PostModel;
import Model.StudentModel;
import POJOS.Post;

/**
 * Servlet implementation class InsertPostServlet
 */
public class InsertPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public InsertPostServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			boolean isDigitalSignaturePresent = DigitalSignatureModel.isDigitalSignaturePresent();
			if (!isDigitalSignaturePresent) {
				request.getServletContext().setAttribute("STATUS", 2);
				request.getRequestDispatcher("ContributeView").forward(request, response);
			} else {
				String postName = request.getParameter("TOPIC_NAME"); 
				String postLink = request.getParameter("LINK"); 
				String postDesc = request.getParameter("DESCRIPTION");
				int postSubjectId = Integer.parseInt(request.getParameter("SUBJECT_ID")); 
				/* We get the details from the FRONTEND and then make a Post POJO out of it */ 
				Post post = new Post(); 
				post.setPostDesc(postDesc);
				post.setPostLink(postLink);
				post.setPostName(postName);
				post.setPostSubjectId(postSubjectId);
				post.setPostIsSpam(true);
				try {
					post.setStudentUsn(StudentModel.getDigitalSignaturePresentStudentData().getStudentUSN());
				} catch (FileNotFoundException | ClassNotFoundException | NoSuchAlgorithmException | SQLException
						| URISyntaxException e1) {
					e1.printStackTrace();
				}
				
				boolean insertIntoPost = PostModel.insertPostRecord(post);
				if (insertIntoPost) {
					request.getServletContext().setAttribute("STATUS", 0);
					request.getRequestDispatcher("ContributeView").forward(request, response);
				} else {
					request.getServletContext().setAttribute("STATUS", 1);
					request.getRequestDispatcher("ContributeView").forward(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("SOME EXCEPTION OCCURED!");
		}
	}
}

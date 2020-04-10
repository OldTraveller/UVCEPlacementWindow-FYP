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

public class EditPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditPostServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("SERVLET : EditPostServlet");
		try {
			boolean isDigitalSignaturePresent = DigitalSignatureModel.isDigitalSignaturePresent();
			if (!isDigitalSignaturePresent) {
				request.getServletContext().setAttribute("STATUS", 2);
				request.getRequestDispatcher("MainPage").forward(request, response);
			} else {
				String postName = request.getParameter("TOPIC_NAME"); 
				String postLink = request.getParameter("LINK"); 
				String postDesc = request.getParameter("DESCRIPTION");
				String postSubjectId = request.getParameter("SUBJECT_ID"); 
				
				Post post = (Post)request.getServletContext().getAttribute("POST_OBJECT"); 
				post.setPostDesc(postDesc);
				post.setPostLink(postLink);
				post.setPostName(postName);
				post.setPostSubjectId(Integer.parseInt(postSubjectId));
				
				boolean editedPost = PostModel.editPostRecord(post);
				if (editedPost) {
					request.getServletContext().setAttribute("STATUS", 0);
					request.getRequestDispatcher("UserWindow").forward(request, response);
				} else {
					request.getServletContext().setAttribute("STATUS", 1);
					request.getRequestDispatcher("UserWindow").forward(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("SOME EXCEPTION OCCURED!");
		} finally {
			System.out.println("MADE THE POST OBJECT TO NULL!");
			request.getServletContext().setAttribute("POST_OBJECT", null);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

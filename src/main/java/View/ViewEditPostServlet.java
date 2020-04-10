package View;

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
import POJOS.Post;

public class ViewEditPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ViewEditPostServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("SERVLET : ViewEditPostServlet");
		try {
			if (!DigitalSignatureModel.isDigitalSignaturePresent()) {
				request.getRequestDispatcher("MainPageView").forward(request, response);
			} else {
				String postId = request.getParameter("postId"); 
				Post post = PostModel.getPostWithPostId(postId); 
				request.getServletContext().setAttribute("POST_OBJECT", post);
				System.out.println("SENDING FOR EDIT TO : post_edit.jsp");
				request.getRequestDispatcher("post_edit.jsp").forward(request, response); 
			}
		} catch (ClassNotFoundException | URISyntaxException | SQLException | ServletException | IOException | NoSuchAlgorithmException e) {
			e.printStackTrace();
			request.getRequestDispatcher("MainPageView").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

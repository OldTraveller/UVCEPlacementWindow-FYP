package Controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.PostModel;

public class TogglePostSpamStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TogglePostSpamStatusServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String postId = request.getParameter("POST_ID"); 
		try {
			System.out.println("TOGGLING THE STATE FOR POST : " + postId); 
			PostModel.toggleSpamStatusForPost(postId);
		} catch (ClassNotFoundException | URISyntaxException | SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("UserWindow#menu2").forward(request, response);
	}

}

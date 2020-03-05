package View;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainPageView extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MainPageView() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MainPageView");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
}

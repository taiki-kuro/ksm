import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		ServletContext context = this.getServletContext();
		Platform platform = (Platform) context.getAttribute("platform");
		if (platform == null) {
			platform = new Platform();
			context.setAttribute("platform", platform);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String studentId = request.getParameter("studentId");
		ServletContext context = this.getServletContext();
		Platform platform = (Platform) context.getAttribute("platform");
		User user = platform.getUserById(studentId);
		if (user == null) {
			user = new User();
			user.setStudentId(studentId);
			platform.addUser(user);
		}
		session.setAttribute("user", user);
		response.sendRedirect("home.html");
	}

}

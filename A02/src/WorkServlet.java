import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/work")
public class WorkServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		ServletContext context = this.getServletContext();
		Work work = (Work) context.getAttribute("work");
		if (work == null) {
			work = new Work();
			context.setAttribute("work", work);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
			String studentId = request.getParameter("studentId");
      String workName = request.getParameter("workName");
      String pass = request.getParameter("pass");
			ServletContext context = this.getServletContext();
			Work work = (Work) context.getAttribute("work");
      User user = new User();
      user.setStudentId(studentId);
      work.setPass(pass);
			work.setStudentId(user);
      work.setWorkName(workName);
      response.sendRedirect("work.html");

	}

}

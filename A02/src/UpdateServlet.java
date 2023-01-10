

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {

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

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user != null) {
			ServletContext context = this.getServletContext();
			Platform platform = (Platform) context.getAttribute("platform");
			StringBuilder builder = new StringBuilder();
			builder.append("{");
			builder.append("\"user\":");
			toJson(user, builder);
			builder.append(",");
			builder.append("\"workList\":[");
			Iterator<Work> workIterator = platform.getWorkList().iterator();
			if (workIterator.hasNext()) {
				Work work = workIterator.next();
				toJson(work, builder);
			}
			while (workIterator.hasNext()) {
				builder.append(",");
				Work work = workIterator.next();
				toJson(work, builder);
			}
			builder.append("]");
			builder.append("}");
			String json = builder.toString();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			PrintWriter writer = response.getWriter();
			writer.append(json);
			writer.flush();
		}
	}

	private static void toJson(User user, StringBuilder builder) {
		builder.append("{");
		builder.append("\"studentId\":\"").append(user.getStudentId()).append("\"");
		builder.append("}");
	}

	private static void toJson(Work work, StringBuilder builder) {
		builder.append("{");
		builder.append("\"user\":");
		toJson(work.getStudentId(), builder);
		builder.append(",");
		builder.append("\"workName\":\"").append(work.getWorkName()).append("\"");
    builder.append(",");
    builder.append("\"pass\":\"").append(work.getPass()).append("\"");
		builder.append("}");
	}

}

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

@WebServlet("/commentUpdate")
public class CommentUpdateServlet extends HttpServlet {

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
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user != null) {
			ServletContext context = this.getServletContext();
      Platform platform = (Platform)context.getAttribute("platform");
			Work work = (Work)context.getAttribute("work");
			StringBuilder builder = new StringBuilder();
			builder.append("{");
			builder.append("\"user\":");
			toJson(user, builder);
			builder.append(",");
      builder.append("\"studentId\":\"").append(work.getStudentId().getStudentId()).append("\",");
      builder.append("\"workName\":\"").append(work.getWorkName()).append("\",");
      builder.append("\"pass\":\"").append(work.getPass()).append("\",");
			builder.append("\"comments\":[");
      Iterator<Comment> commentIterator = work.getcomments().iterator();
			if (commentIterator.hasNext()) {
				Comment comment = commentIterator.next();
        toJson(comment, builder);
			}
			while (commentIterator.hasNext()) {
				builder.append(",");
				Comment comment = commentIterator.next();
				toJson(comment, builder);
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

	private static void toJson(Comment comment, StringBuilder builder) {
		builder.append("{");
		builder.append("\"user\":");
		toJson(comment.getStudentId(), builder);
		builder.append(",");
		builder.append("\"comment\":\"").append(comment.getComment()).append("\"");
		builder.append("}");
	}

}

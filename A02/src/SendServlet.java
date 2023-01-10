import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/send")
public class SendServlet extends HttpServlet {

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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		User studentId = (User) session.getAttribute("user");
		if (studentId != null) {
			String commentString = request.getParameter("comment");
			ServletContext context = this.getServletContext();
			Work work = (Work) context.getAttribute("work");
			Platform platform = (Platform)context.getAttribute("platform");
			Comment comment = new Comment();
			comment.setStudentId(studentId);
			comment.setComment(commentString);
			work.addComment(comment);
			Work mainWork = new Work();
			for(int i = 0; i < platform.getWorkList().size(); i++){
				Work subWork = platform.getWorkList().get(i);
				System.out.println(subWork.getStudentId().getStudentId());
				if(subWork.getStudentId().getStudentId().equals(work.getStudentId().getStudentId())){
					if(subWork.getWorkName().equals(work.getWorkName())){
						mainWork = subWork;
						for(Comment subComment : work.getcomments()){
							mainWork.addComment(subComment);
						}
					}
				}
			}
		}
	}

}

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

@WebServlet("/workUpdate")
public class WorkUpdateServlet extends HttpServlet {

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
      Work work = (Work) context.getAttribute("work");
      Work mainWork = new Work();
      System.out.println(work.getStudentId().getStudentId());
      for(int i = 0; i < platform.getWorkList().size(); i++){
        Work subWork = platform.getWorkList().get(i);
        System.out.println(subWork.getStudentId().getStudentId());
        if(subWork.getStudentId().getStudentId().equals(work.getStudentId().getStudentId())){
          if(subWork.getWorkName().equals(work.getWorkName())){
            mainWork = subWork;
          }
        }
      }
			StringBuilder builder = new StringBuilder();
			builder.append("{");
      builder.append("\"studentId\":\"").append(mainWork.getStudentId().getStudentId()).append("\",");
      builder.append("\"workName\":\"").append(mainWork.getWorkName()).append("\".");
      builder.append("\"pass\":\"").append(mainWork.getPass()).append("\"");
			builder.append("}");
			String json = builder.toString();
      System.out.println(json);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			PrintWriter writer = response.getWriter();
			writer.append(json);
			writer.flush();
		}
	}
}

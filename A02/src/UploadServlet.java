import java.io.IOException;
import java.io.File;
import java.nio.file.Paths;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.servlet.RequestDispatcher;


@WebServlet("/upload")
@MultipartConfig(location = "/tmp/")
public class UploadServlet extends HttpServlet {

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
    User user = (User) session.getAttribute("user");
    if (user != null) {
        String text = request.getParameter("text");
        System.out.println(text);
        String workName = request.getParameter("workName");
        ServletContext context = this.getServletContext();
        Platform platform = (Platform) context.getAttribute("platform");
        Work work = new Work();
        work.setStudentId(user);
        work.setWorkName(workName);
        work.setPass(text);
        platform.addWork(work);
      }
		/*Part part = request.getPart("file");
		String filename = part.getSubmittedFileName();
		String path = getServletContext().getRealPath("/uploaded");
		System.out.println(path);
		part.write(path + "/" + filename);
		request.setAttribute("filename", filename);*/
    RequestDispatcher dispatcher = request.getRequestDispatcher("home.html");
    dispatcher.forward(request, response);
    //response.sendRedirect("home.html");

 }

}

package portit.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;


/**
 * 게시물 작성 서블릿
 *
 */
@SuppressWarnings("serial")
@WebServlet("/post")
public class PostServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// GET 요청일 경우 등록 페이지로 포워딩
		String articleType = req.getParameter("post");
		String actionType = req.getParameter("type");
		RequestDispatcher rd = null;
		if ("profile".equals(articleType)) {
			rd = req.getRequestDispatcher("");
			rd.forward(req, resp);
		} else if ("portfolio".equals(articleType)) {
			rd = req.getRequestDispatcher("/page?page=myPfRegister");
			rd.forward(req, resp);
		} else if ("project".equals(articleType)) {
			rd = req.getRequestDispatcher("");
			rd.forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// POST 요청일 때 등록 처리
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		if (!ServletFileUpload.isMultipartContent(req)) {
			try {
				throw new Exception("요청이 multipart/form-data로 인코딩되지 않았습니다.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		FileUploadController uploadController = new FileUploadController();
		List<String> fileNames = uploadController.fileUpload(req, resp);
		req.setAttribute("fileNames", fileNames);
		
		String[] viewUrl = null;
		
		String articleType = req.getParameter("type");
		if ("profile".equals(articleType)) {
			/*Controller profileController = ControllerFactory.getInstance().createController("profile");
			viewUrl = profileController.execute(req, resp);*/
		} else if ("portfolio".equals(articleType)) {
			Controller portfolioAddController = ControllerFactory.getInstance().createController("portfolioAdd");
			viewUrl = portfolioAddController.execute(req, resp);
		} else if ("project".equals(articleType)) {
			/*Controller projectController = ControllerFactory.getInstance().createController("project");
			viewUrl = projectController.execute(req, resp);*/
		}
		
		RequestDispatcher rd = null;
		if (viewUrl[0].startsWith("inc")) {
			rd = req.getRequestDispatcher(viewUrl[1]);
			rd.include(req, resp);
		} else if (viewUrl[0].startsWith("fwd")) {
			rd = req.getRequestDispatcher(viewUrl[1]);
			rd.forward(req, resp);
		} else {
			resp.sendRedirect(viewUrl[1]);
		}
	}

}

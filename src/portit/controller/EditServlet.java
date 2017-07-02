package portit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;


/**
 * 게시물 수정 서블릿
 *
 */
@SuppressWarnings("serial")
@WebServlet("/edit")
public class EditServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// GET 요청일 경우 이동
		resp.sendRedirect("/page?page=main");
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
		uploadController.fileUpload(req, resp);
		req.setAttribute("formData", req.getAttribute("formData"));
		req.setAttribute("fileNames", req.getAttribute("fileNames"));
		req.setAttribute("id", Integer.parseInt(req.getParameter("id")));
		
		String viewUrl = "";
		String articleType = req.getParameter("type");
		if ("profile".equals(articleType)) {
			/*Controller profileController = ControllerFactory.getInstance().createController("profile");
			viewUrl = profileController.execute(req, resp);*/
		} else if ("portfolio".equals(articleType)) {
			PortfolioEditController portfolioEditController = new PortfolioEditController();
			viewUrl = portfolioEditController.execute(req, resp);
		} else if ("project".equals(articleType)) {
			/*Controller projectController = ControllerFactory.getInstance().createController("project");
			viewUrl = projectController.execute(req, resp);*/
		}
		System.out.println("viewUrl: " + viewUrl.substring(0, 3) + "/" + viewUrl.substring(4));
		RequestDispatcher rd = null;
		if (viewUrl.substring(0, 3).equals("inc")) {
			rd = req.getRequestDispatcher(viewUrl.substring(4));
			rd.include(req, resp);
		} else if (viewUrl.substring(0, 3).equals("fwd")) {
			rd = req.getRequestDispatcher(viewUrl.substring(4));
			rd.forward(req, resp);
		} else {
			resp.sendRedirect(viewUrl.substring(4));
		}
	}

}

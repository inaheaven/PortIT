package portit.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import portit.controller.Controller;
import portit.controller.ControllerFactory;
import portit.util.FileUpload;


/**
 * 게시물 작성 서블릿
 *
 */
@SuppressWarnings("serial")
@WebServlet("/post")
public class PostServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// GET 요청일 경우 등록화면으로
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		String articleType = req.getParameter("type");
		RequestDispatcher rd = null;
		if ("profile".equals(articleType)) {
			rd = req.getRequestDispatcher("/MyProf.jsp");
		} else if ("portfolio".equals(articleType)) {
			rd = req.getRequestDispatcher("/MyPfRegister.jsp");
		} else if ("project".equals(articleType)) {
			rd = req.getRequestDispatcher("/MyProjRegister.jsp");
		}
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// POST 요청일 때 등록 처리
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		// 요청을 FileUpload 객체로 보내 먼저 처리시키고 Map을 받음 
		FileUpload fileUpload = new FileUpload();
		Map<String, Object> formData = fileUpload.fileUpload(req, resp);
		
		// 컨트롤러에 전달하기 위해 요청에 세팅
		req.setAttribute("formData", formData);
		
		String viewUrl = ""; // 컨트롤러는 처리 결과 viewUrl을 반환할 것
		String articleType = req.getParameter("type"); // 작성할 게시물의 구분
		
		// 컨트롤러의 인스턴스를 생성하고 실행 메서드 호출 
		ControllerFactory factory = ControllerFactory.getInstance();
		if ("profile".equals(articleType)) {
			Controller profileAddController = factory.newController("profileAdd");
			viewUrl = profileAddController.execute(req, resp);
		} else if ("portfolio".equals(articleType)) {
			Controller portfolioAddController = factory.newController("portfolioAdd");
			viewUrl = portfolioAddController.execute(req, resp);
		} else if ("project".equals(articleType)) {
			Controller projectAddController = factory.newController("projectAdd");
			viewUrl = projectAddController.execute(req, resp);
		}
		System.out.println("viewUrl: " + viewUrl.substring(0, 3) + "/" + viewUrl.substring(4));
		
		// viewUrl의 시작부분에 따라 인클루딩/포워딩/리다이렉트 분리
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

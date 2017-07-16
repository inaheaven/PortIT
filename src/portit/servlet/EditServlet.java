package portit.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import portit.controller.Controller;
import portit.controller.ControllerFactory;
import portit.model.dao.PortfolioDao;
import portit.model.dao.ProfileDao;
import portit.model.dao.ProjectDao;
import portit.model.dto.Portfolio;
import portit.util.FileUpload;


/**
 * 게시물 수정 서블릿
 *
 */
@SuppressWarnings("serial")
@WebServlet("/edit")
public class EditServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("==== " + this.getClass().getSimpleName() + " ====");
		System.out.println("---- doGet() ----");
		// GET 요청일 경우 폼 화면으로
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		String articleType = req.getParameter("type");
		RequestDispatcher rd = null;
		if ("profile".equals(articleType)) {
			String articleId = req.getParameter("id"); // 닉네임
			req.setAttribute("id", articleId);
			ProfileDao profileDao = new ProfileDao();
			req.setAttribute("profile", profileDao.getProfileByNick(articleId));
			rd = req.getRequestDispatcher("/page?page=myProfUpdate&type="+articleType+"&id="+articleId);
		} else if ("portfolio".equals(articleType)) {
			int articleId = Integer.parseInt(req.getParameter("id")); // 게시물 번호
			req.setAttribute("id", articleId);
			PortfolioDao portfolioDao = new PortfolioDao();
			req.setAttribute("portfolio", portfolioDao.selectOne(articleId));
			rd = req.getRequestDispatcher("/page?page=myPfUpdate&type="+articleType+"&id="+articleId);
		} else if ("project".equals(articleType)) {
			int articleId = Integer.parseInt(req.getParameter("id")); // 게시물 번호
			req.setAttribute("id", articleId);
			ProjectDao projectDao = new ProjectDao();
			req.setAttribute("project", projectDao.getProjectById(articleId));
			rd = req.getRequestDispatcher("/page?page=myProjRegisterUpdate&type="+articleType+"&id="+articleId);
		}
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("==== " + this.getClass().getSimpleName() + " ====");
		System.out.println("---- doPost() ----");
		// POST 요청일 때 등록 처리
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		HttpSession session = req.getSession();

		// 요청을 FileUpload 객체로 보내 먼저 처리시키고 Map을 받음 
		FileUpload fileUpload = new FileUpload();
		Map<String, Object> formData = fileUpload.fileUpload(req, resp);
		
		// 컨트롤러에 전달하기 위해 요청에 세팅
		req.setAttribute("formData", formData);
		
		String viewUrl = ""; // 컨트롤러는 처리 결과 viewUrl을 반환할 것
		//String articleType = req.getParameter("type"); // 작성할 게시물의 구분
		String articleType = formData.get("type").toString(); // 작성할 게시물의 구분
		
		// 컨트롤러의 인스턴스를 생성하고 실행 메서드 호출 
		ControllerFactory factory = ControllerFactory.getInstance();
		if ("profile".equals(articleType)) {
			String articleId = formData.get("prof_nick").toString(); // 닉네임
			req.setAttribute("id", articleId);
			req.setAttribute("profile", session.getAttribute("profile"));
			Controller profileEditController = factory.newController("profileEdit");
			viewUrl = profileEditController.execute(req, resp);
		} else if ("portfolio".equals(articleType)) {
			//int articleId = Integer.parseInt(req.getParameter("pf_id")); // 게시물 번호
			int articleId = Integer.parseInt(formData.get("pf_id").toString());
			req.setAttribute("id", articleId);
			Portfolio portfolio = (Portfolio) session.getAttribute("portfolio");
			System.out.println("portfolio - "+portfolio);
			req.setAttribute("portfolio", portfolio);
			Controller portfolioEditController = factory.newController("portfolioEdit");
			viewUrl = portfolioEditController.execute(req, resp);
		} else if ("project".equals(articleType)) {
			int articleId = Integer.parseInt(formData.get("proj_id").toString()); // 게시물 번호
			req.setAttribute("id", articleId);
			req.setAttribute("project", session.getAttribute("project"));
			Controller projectEditController = factory.newController("projectEdit");
			viewUrl = projectEditController.execute(req, resp);
		}
		System.out.println("viewUrl: " + viewUrl);
		
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
package portit.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import portit.controller.Controller;
import portit.controller.ControllerFactory;


/**
 * 게시물 조회 서블릿
 *
 */
@SuppressWarnings("serial")
@WebServlet("/view")
public class ViewDetailServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		// 프로필 글 주소 예:		/view?type=profile&id=username
		// 포트폴리오 글 주소 예:	/view?type=portfolio&id=1234
		// 프로젝트 글 주소 예:		/view?type=project&id=1234
		
		String articleType = req.getParameter("type"); // 작성할 게시물의 구분
		
		// 컨트롤러는 처리 결과 viewUrl을 반환할 것
		String viewUrl = "";
		// 컨트롤러의 인스턴스를 생성하고 실행 메서드 호출 
		ControllerFactory factory = ControllerFactory.getInstance();
		if ("profile".equals(articleType)) {			
			String articleId = req.getParameter("id"); // 닉네임
			req.setAttribute("id", articleId);
			Controller profileViewController = factory.newController("profileView");
			viewUrl = profileViewController.execute(req, resp);
			req.setAttribute("profile", req.getAttribute("profile"));
		} else if ("portfolio".equals(articleType)) {
			int articleId = Integer.parseInt(req.getParameter("id")); // 게시물 번호
			req.setAttribute("id", articleId);
			Controller portfolioViewController = factory.newController("portfolioView");
			viewUrl = portfolioViewController.execute(req, resp);
			req.setAttribute("portfolio", req.getAttribute("portfolio"));
		} else if ("project".equals(articleType)) {
			int articleId = Integer.parseInt(req.getParameter("id")); // 게시물 번호
			req.setAttribute("id", articleId);
			Controller projectViewController = factory.newController("projectView");
			viewUrl = projectViewController.execute(req, resp);
			req.setAttribute("project", req.getAttribute("project"));
		}
		System.out.println("viewUrl: " + viewUrl.substring(0, 3) + "/" + viewUrl.substring(4));
		
		// viewUrl로 포워딩
		RequestDispatcher rd = req.getRequestDispatcher(viewUrl.substring(4));
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}

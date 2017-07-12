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
 * 게시물 삭제 서블릿
 *
 */
@SuppressWarnings("serial")
@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// POST 요청일 때만 삭제 처리
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		String viewUrl = ""; // 컨트롤러는 처리 결과 viewUrl을 반환할 것		
		String articleType = req.getParameter("type"); // 작성할 게시물의 구분
		
		// 컨트롤러의 인스턴스를 생성하고 실행 메서드 호출 
		ControllerFactory factory = ControllerFactory.getInstance();
		if ("profile".equals(articleType)) {
			String prof_nick = req.getParameter("id");
			Controller profileEditController = factory.newController("profileEdit");
			req.setAttribute("prof_nick", prof_nick);
			viewUrl = profileEditController.execute(req, resp);
		} else if ("portfolio".equals(articleType)) {
			int pf_id = Integer.parseInt(req.getParameter("id"));
			Controller portfolioEditController = factory.newController("portfolioEdit");
			req.setAttribute("pf_id", pf_id);
			viewUrl = portfolioEditController.execute(req, resp);
		} else if ("project".equals(articleType)) {
			int pf_id = Integer.parseInt(req.getParameter("id"));
			Controller projectEditController = factory.newController("projectEdit");
			req.setAttribute("pf_id", pf_id);
			viewUrl = projectEditController.execute(req, resp);
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
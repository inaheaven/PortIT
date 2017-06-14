package portit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import portit.model.dto.Member;

/**
 * 프론트 컨트롤러
 * 클라이언트의 서블릿 요청을 받아 기능을 해당 컨트롤러에게 분배하고 해당 컨트롤러의 처리 결과를 뷰에게 전달
 * @author gnsngck
 *
 */
@SuppressWarnings("serial")
@WebServlet("*.do") /* HTML/JSP에서 .do로 끝나는 요청은 모두 서블릿 요청으로 간주하여 프론트 컨트롤러가 처리 */
public class FrontController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Servlet Loaded");
		resp.setContentType("text/html; charset=UTF-8");

		try {
			ControllerFactory factory = ControllerFactory.getInstance();
			Controller controller = factory.createController(req.getServletPath());

			// 인클루드하여 처리 위임
			RequestDispatcher rd = req.getRequestDispatcher("");
			rd.include(req, resp);

			// 담당 컨트롤러가 반환하는 뷰의 URL을 이용하여 뷰에게 실행을 위임
			// 조건에 따라 위임 방식으로 포워딩 혹은 인클루딩을 사용
			String viewUrl = (String) req.getAttribute("viewUrl");
			rd = req.getRequestDispatcher(viewUrl);
			rd.include(req, resp);
			//rd.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			// 에러를 세션에 저장
			req.setAttribute("error", e);
			// 에러페이지로
			RequestDispatcher rd = req.getRequestDispatcher("");
			rd.forward(req, resp);
		}
	}

}

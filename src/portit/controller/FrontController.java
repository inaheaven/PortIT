package portit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 프론트 컨트롤러
 * 
 * @author gnsngck
 *
 */
@SuppressWarnings("serial")
@WebServlet("/*.do") /* HTML/JSP에서 .do로 끝나는 요청은 모두 서블릿 요청으로 간주하여 프론트 컨트롤러가 처리 */
public class FrontController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		// 클라이언트가 요청한 주소 알아내기
		String requestPath = req.getServletPath();
		String controllerPath = null;
		try {
			// 요청에 따른 분기
			if ("/join.do".equals(requestPath)) {
				controllerPath = "/join";
				// 클라이언트 입력값을 페이지 컨트롤러에 전달
			}

			// 컨트롤러에 처리 위임
			RequestDispatcher rd = req.getRequestDispatcher(controllerPath);
			rd.include(req, resp);
			
			// 컨트롤러 처리 결과 뷰 URL 반환
			String viewUrl = null;
			
			// 뷰 실행
			rd = req.getRequestDispatcher(viewUrl);
			rd.include(req, resp);
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

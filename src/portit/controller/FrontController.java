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
 * 
 * @author gnsngck
 *
 */
@SuppressWarnings("serial")
public class FrontController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("프론트 Ctrl");
		resp.setContentType("text/html; charset=UTF-8");

		// 클라이언트가 요청한 주소 알아내기
		String requestPath = req.getServletPath();
		try {

			String controllerPath = null;
			if ("/join".equals(requestPath)) {
				controllerPath = "/join";
				// 회원가입 정보를 저장
				req.setAttribute("member", new Member()
						.setMem_email(req.getParameter("email"))
						.setMem_password(req.getParameter("password"))
						);
			}

			// 인클루드하여 처리 위임
			RequestDispatcher rd = req.getRequestDispatcher(controllerPath);
			rd.include(req, resp);

			String viewURL = (String) req.getAttribute("viewURL");
			rd = req.getRequestDispatcher(viewURL);
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

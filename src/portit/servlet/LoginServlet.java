package portit.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import portit.controller.Controller;
import portit.controller.ControllerFactory;

@SuppressWarnings("serial")
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("/index.jsp");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		// 폼에 입력된 이메일과 비밀번호를 RequestServlet에 저장
		req.setAttribute("loginEmail", req.getParameter("userid"));
		req.setAttribute("loginPwd", req.getParameter("userpw"));

		// 로그인 처리를 위한 컨트롤러
		Controller memberLoginController = ControllerFactory.getInstance().newController("MemberLogin");
		String viewUrl = memberLoginController.execute(req, resp);
		
		if (viewUrl != null) {
			if (viewUrl.substring(0,4).equals("fwd:")) {
				RequestDispatcher rd = req.getRequestDispatcher(viewUrl.substring(4));
				rd.forward(req, resp);
			} else if (viewUrl.substring(0,4).equals("inc:")) {
				RequestDispatcher rd = req.getRequestDispatcher(viewUrl.substring(4));
				rd.include(req, resp);
			} else {
				resp.sendRedirect(viewUrl.substring(4));
			}
		} else {
			out.println("script type=\"text/javascript\">window.alert(\"이메일 주소나 비밀번호가 틀립니다.\");</script>");
			resp.sendRedirect("/page?page=main");
		}		
	}
	
}

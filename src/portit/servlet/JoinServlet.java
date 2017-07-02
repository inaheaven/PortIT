package portit.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import portit.controller.Controller;
import portit.controller.ControllerFactory;
import portit.model.dao.MemberDao;
import portit.model.dto.Member;

@SuppressWarnings("serial")
@WebServlet("/join")
public class JoinServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		resp.sendRedirect("/signin.jsp");
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		// 폼에 입력된 이메일과 비밀번호를 RequestServlet에 저장
		req.setAttribute("joinEmail", req.getParameter("userid"));
		req.setAttribute("joinPwd", req.getParameter("userpw"));
		
		// 중복가입 체크
		MemberDao memberDao = new MemberDao();
		if (memberDao.selectOneByEmail(req.getParameter("userid")) == null) {
			// 가입 처리를 위한 컨트롤러
			Controller memberAddController = ControllerFactory.getInstance().newController("MemberAdd");
			String viewUrl = memberAddController.execute(req, resp);
			
			out.println("script type=\"text/javascript\">window.alert(\"회원가입이 완료되었습니다.\n로그인해주세요.\");</script>");
			
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
			out.println("script type=\"text/javascript\">window.alert(\"이미 등록된 메일주소입니다.\");</script>");
			resp.sendRedirect("/index.jsp");
		}
		
	}
}
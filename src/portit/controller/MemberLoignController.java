package portit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import portit.model.dao.MemberDao;
import portit.model.dto.Member;


@WebServlet("/login")
public class MemberLoignController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		Member member = new Member();
		member.setMem_email((String)req.getParameter("userid"));
		member.setMem_password((String)req.getParameter("userpw"));
		
		MemberDao memberDao = new MemberDao();
		Member checkMem = memberDao.search(member); // 검사 용도
		
		if(member.getMem_email().equals(checkMem.getMem_email()) && member.getMem_password().equals(checkMem.getMem_password())) {
			// 세션에 로그인 정보 넣기
			HttpSession session = req.getSession(true);
			session.setAttribute("loginId", checkMem.getMem_id());
			session.setAttribute("loginEmail", checkMem.getMem_email());
			session.setAttribute("loginPw", checkMem.getMem_password());
			
			out.println("<script>location.href='/page?page=main';</script>");
		}
		else {
			out.println("<script>alert('이메일 또는 비밀번호가 틀렸습니다.'); location.href='/index.jsp';</script>");			
		}	
	}
}








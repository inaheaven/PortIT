package portit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import portit.model.dao.MemberDao;
import portit.model.dto.Member;

@WebServlet("/join")
public class MemberJoinController extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String userpw = req.getParameter("userpw");
		String userpwcf = req.getParameter("userpwcf");
		
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		Member member = new Member();
		member.setMem_email((String)req.getParameter("userid"));
		member.setMem_password((String)req.getParameter("userpw"));
		
		MemberDao memberDao = new MemberDao();
		Member checkMem = memberDao.search(member);
		
		if(checkMem.getMem_id() == 0) { // 등록된 아이디가 없을 때 - 회원가입 가능
			memberDao.insert(member);
			out.println("<script>alert('가입되었습니다! 로그인을 해주세요.'); location.href='/index.jsp';</script>");	
		}
		else { 
			out.println("<script>alert('이메일 아이디가 이미 존재합니다.'); location.href='/index.jsp';</script>");			
			
		}
	}
}
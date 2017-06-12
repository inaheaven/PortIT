package portit.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import portit.model.dao.MemberDao;
import portit.model.dto.Member;

@WebServlet("/msg")
public class MessageController extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		resp.setContentType("text/html; charset=UTF-8");
		
		try {
			// request에서 dto로 옮겨담기.
			
			
			
			ServletContext sc = this.getServletContext();
			MemberDao memberDao = (MemberDao) sc.getAttribute("member");
			Member member = (Member) req.getAttribute("member");
			// DAO의 메서드 호출
			// 같은 회원 번호를 가진 회원이 있는지 검사
			Member existingMember = memberDao.findExistingMember(member.getMem_id(), member.getMem_email());
			if (member.getMem_id() != existingMember.getMem_id()) {
				// 같은 회원 번호를 가진 회원이 있다면 회원 번호를 재설정
				member.resetMem_id();
			}
			// 가입 메서드 호출
			memberDao.insert(member);
			
			// 가입처리 후 이동할 페이지 지정
			req.setAttribute("viewURL", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
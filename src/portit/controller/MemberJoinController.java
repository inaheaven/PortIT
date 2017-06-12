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

@WebServlet("/join")
public class MemberJoinController extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		// GET 요청일 경우 Front Controller에 가입 페이지 제공
		// (주소로 접속, 링크 클릭)
		req.setAttribute("viewUrl", "");
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		// POST 요청일 경우 가입 처리
		// (폼 데이터 전송)
		try {
			// ServletContext에서 요청 정보 꺼내오기
			ServletContext sc = this.getServletContext();
			MemberDao memberDao = (MemberDao) sc.getAttribute("member");
			Member member = (Member) req.getAttribute("member");
			// DAO의 메서드 호출
			memberDao.insert(member);
			
			// 가입처리 후 이동할 페이지 지정
			req.setAttribute("viewURL", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

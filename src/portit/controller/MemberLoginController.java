package portit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import portit.model.dao.MemberDao;
import portit.model.dto.Member;

public class MemberLoginController implements Controller {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		MemberDao memberDao = new MemberDao();
		Member member = memberDao.selectOneByLogin((String) req.getAttribute("loginEmail"), (String) req.getAttribute("loginPwd"));
		if (member.getMem_email().equals((String) req.getAttribute("loginEmail"))
				&& member.getMem_password().equals((String) req.getAttribute("loginPwd"))) {
			HttpSession session = req.getSession();
			session.setAttribute("loginId", member.getMem_id());
			session.setAttribute("loginEmail", member.getMem_email());
			return "rdr:/page?page=main";
		} else {
			return null;
		}
		
	}
	
}








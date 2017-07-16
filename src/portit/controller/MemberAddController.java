package portit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import portit.model.dao.MemberDao;
import portit.model.dto.Member;

/**
 * 회원가입 컨트롤러
 *
 */
public class MemberAddController implements Controller {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Member member = new Member().setMem_email((String) req.getAttribute("joinEmail"))
				.setMem_password((String) req.getAttribute("joinPwd"));
		MemberDao memberDao = new MemberDao();
		if (memberDao.insert(member) > 0) {
			return "rdr:/login";
		} else {
			return null;
		}
	}
	
}

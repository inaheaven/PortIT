package portit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import portit.model.dao.ProfileDao;
import portit.model.dto.Profile;

/**
 * 프로필 조회 컨트롤러
 *
 */
public class ProfileViewController implements Controller {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		ProfileDao profileDao = ProfileDao.getInstance();
		
		// 닉네임으로 프로필 번호를 받아오기
		//int mem_id = profileDao.nickToId(req.getParameter("id"));
		
		// 조회 결과가 저장된 프로필 DTO를 획득하여 요청에 세팅
		Profile profile = profileDao.getProfileByNick(req.getParameter("id"));
		req.setAttribute("profile", profile);
		
		// 뷰 URL 반환
		String viewUrl = "fwd:/memDetail.jsp";
		return viewUrl;
	}

}
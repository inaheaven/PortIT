package portit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import portit.model.dao.ProfileDao;
import portit.model.dto.Profile;

public class ProfileViewController implements Controller {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		ProfileDao profileDao = new ProfileDao();
		Profile profile = profileDao.selectOneByMemId((int) req.getSession().getAttribute("loginId"));
		req.setAttribute("profile", profile);
		
		return "fwd:/profile?username="+req.getAttribute("username");
	}
}

package portit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import portit.model.dao.ProfileDao;

public class ProfileDeleteController implements Controller {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		ProfileDao profileDao = new ProfileDao();
		profileDao.deleteByMemId((int) req.getSession().getAttribute("loginId"));
		
		return "rdr:/page?page=main";
	}

}

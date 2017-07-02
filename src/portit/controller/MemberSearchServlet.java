package portit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import portit.model.dao.ProfileDao;

@SuppressWarnings("serial")
@WebServlet("/form/memSearch")
public class MemberSearchServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		
		String username = req.getParameter("username");
		
		ProfileDao profileDao = new ProfileDao();
		JSONArray arr = profileDao.selectJson(username);
		
	}
	
}

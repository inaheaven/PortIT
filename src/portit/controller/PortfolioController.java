package portit.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import portit.model.dao.MemberDao;
import portit.model.dto.Portfolio;

@SuppressWarnings("serial")
@WebServlet("/portfolios")
public class PortfolioController extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		
		Map<String, String[]> map = req.getParameterMap();
		for(int i = 0; i < map.size(); i++) {
			System.out.println(map.get(i));
		}
	}
	
}

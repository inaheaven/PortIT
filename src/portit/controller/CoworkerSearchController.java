package portit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import portit.model.dao.ProjectDao;
import portit.model.dao.SearchDao;
import portit.model.dto.Profile;

@WebServlet("/coworker_search")
public class CoworkerSearchController extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		System.out.println("Servlet Loaded!!!");
		
		req.setCharacterEncoding("UTF-8");		
		ProjectDao dao = new ProjectDao();
		dao.findMemberbyName(req, resp);
		
		// 포트폴리오 공동 작업자 찾기
		resp.setContentType("text/xml; charset=UTF-8");
		SearchDao searchDao = new SearchDao();
		String name = req.getParameter("name");
		List<Profile> findResult = searchDao.searchAll_member(name, false);
		
		PrintWriter out = resp.getWriter();
		String xml = "<users>";
		for (Profile prof : findResult) {
			xml += "<user>";
			xml += "<name>"+prof.getProf_name()+"</name>";
			xml += "<nick>"+prof.getProf_nick()+"</nick>";
			xml += "</users>";
		}
		xml += "</users>";
		resp.getWriter().println(xml);
	}
}
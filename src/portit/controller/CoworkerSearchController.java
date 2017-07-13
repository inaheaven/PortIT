package portit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import portit.model.dao.ProjectDao;
import portit.model.db.DBConnectionMgr;
import portit.model.dto.Profile;

@WebServlet("/coworker_search")
public class CoworkerSearchController extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		System.out.println("Servlet Loaded!!!");
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		ProjectDao dao = new ProjectDao();
		dao.findMemberbyName(req, resp);
		
		
		// 포트폴리오 공동 작업자 찾기
		String input = req.getParameter("input");		
		PrintWriter out = resp.getWriter();
		
		String xml = "<users>";
		for (Profile profile : getProfilesByName(input)) {
			xml += "<user>";
			xml += "<nick>"+profile.getProf_nick()+"</nick>";
			xml += "<name>"+profile.getProf_name()+"</name>";
			xml += "</user>";
		}
		xml += "</users>";
		resp.getWriter().println(xml);
		
	}
	
	/**
	 * prof_nick 혹은 prof_name으로 프로필을 받아오는 메서드
	 * (병합 시 ProfileDao로 이동시키셔도 되겠습니다)
	 * @param name
	 * @return
	 */
	private List<Profile> getProfilesByName(String name) {
		DBConnectionMgr pool = DBConnectionMgr.getInstance();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<Profile> profileList = new ArrayList<Profile>();
		try {
			conn = pool.getConnection();
			String sql = "SELECT * FROM profile WHERE prof_nick LIKE '%' || ? || '%' OR prof_name LIKE '%' || ? || '%'";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, name);
			rs = stmt.executeQuery();
			while (rs.next()) {
				profileList.add(new Profile().setProf_nick(rs.getString("prof_nick")).setProf_name(rs.getString("prof_name")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(conn, stmt, rs);
		}
		return profileList;
	}
}
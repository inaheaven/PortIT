package portit.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import portit.model.dao.BookmarkDao;
import portit.model.dao.FollowDao;
import portit.model.dto.Follow;
import portit.model.dto.Portfolio;

@WebServlet("/follow")
public class FollowController extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		resp.setContentType("text/html; charset=UTF-8");

		FollowDao fw = new FollowDao();
		
		HttpSession session = req.getSession();
		int loginId = (int) session.getAttribute("loginId");
		String act = req.getParameter("act");
		
		// 팔로우 삭제 ( 마이페이지에서 )
		if (act.equals("delete")) {
			int fw_id = Integer.parseInt(req.getParameter("fw_id"));
			fw.delete(fw_id);
			
			resp.sendRedirect("/page?page=myFollowing");
		}
		
		// 팔로우 등록
		else if (act.equals("add")) {
			int mem_id = Integer.parseInt(req.getParameter("mem_id"));
			fw.insert(loginId, mem_id);
			
			resp.sendRedirect(""); // 해당 멤버 디테일 페이지로 
		}
		
		// 팔로우 취소 ( 멤버 상세 페이지에서 )
		else if (act.equals("cancel")) {
			int mem_id = Integer.parseInt(req.getParameter("mem_id"));
			fw.cancel(loginId, mem_id);
		}
	}

}


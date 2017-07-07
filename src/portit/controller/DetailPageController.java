package portit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import portit.model.dao.ProfileDao;

@WebServlet("/detail")
public class DetailPageController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String forPage = req.getParameter("for");
		
		int id = Integer.parseInt(req.getParameter("id"));
		
		ProfileDao prof =  new ProfileDao();
		
		if(forPage.equals("mem")) {// 멤버 상세
			req.setAttribute("profile", prof.selectForDetail(id));
			resp.sendRedirect("memDetail.jsp");
		}
		else if(forPage.equals("pf")) {// 포트폴리오 상세
			resp.sendRedirect("pfDetail.jsp?id="+id);
		}
		else if(forPage.equals("proj")) {// 프로젝트 상세
			resp.sendRedirect("projDetail.jsp?id="+id);
		}
	}
}

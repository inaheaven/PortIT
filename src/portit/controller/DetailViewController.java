package portit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import portit.model.dao.ViewDao;

@WebServlet(urlPatterns="/detailView")
public class DetailViewController extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		
		int pf_id = Integer.parseInt(req.getParameter("pf_id"));
		int prof_id = Integer.parseInt(req.getParameter("prof_id"));
		int proj_id = Integer.parseInt(req.getParameter("proj_id"));
		String tag_name = req.getParameter("tag_name");
		String cmd = req.getParameter("cmd");
		String url = null;
		
		ViewDao viewdao = new ViewDao();		
	
		if(cmd.equals("PORTFOLIO")){
			req.setAttribute("pf_view", viewdao.portfolio_info(pf_id));	//이름만 바꿔주면 됨.
			url="/page?page=";
		}
		else if(cmd.equals("MEMBER")){
			req.setAttribute("prof_view",viewdao.member_info(prof_id));
			url="/page?page=";
		}
		else if(cmd.equals("PROJECT")){
			req.setAttribute("proj_view",viewdao.project_info(proj_id));			
			url="/page?page=";
		}
		else if(cmd.equals("TAG")){
			req.setAttribute("proj_view",viewdao.project_info(proj_id));			
			url="/page?page=";
		}
		

		RequestDispatcher view = req.getRequestDispatcher(url);
		view.forward(req,resp);
	}
}
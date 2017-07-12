package portit.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import portit.model.dao.SearchDao;

@WebServlet("/detailView")
public class DetailViewController extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		
		String cmd = req.getParameter("cmd");
	
		String url = null;
		
		SearchDao searchDao = new SearchDao();		
	
		if(cmd.equals("PORTFOLIO")){
			//int pf_id = Integer.parseInt(req.getParameter("pf_id"));
			//req.setAttribute("pf_view", viewdao.portfolio_info(pf_id));	//이름만 바꿔주면 됨.
			url="/page?page=searchAll";
		}
		else if(cmd.equals("MEMBER")){
			//int prof_id = Integer.parseInt(req.getParameter("prof_id"));
			//req.setAttribute("prof_view",viewdao.member_info(prof_id));
			url="/page?page=";
		}
		else if(cmd.equals("PROJECT")){
			//int proj_id = Integer.parseInt(req.getParameter("proj_id"));
			//req.setAttribute("proj_view",viewdao.project_info(proj_id));			
			url="/page?page=";
		}
		else if(cmd.equals("TAG")){
			String tag_name = req.getParameter("tag_name");
			System.out.println(tag_name);
			tag_name = tag_name.toUpperCase();
			List port_list = searchDao.searchAll_port(tag_name,true);
			List mem_list = searchDao.searchAll_member(tag_name,true);
			List proj_list = searchDao.searchAll_proj(tag_name,true);	
		
			//Model에서 가지고 온 정보를 View에 넘겨주기 위해 변수 선언
			req.setAttribute("port_list", port_list);
			req.setAttribute("mem_list", mem_list);
			req.setAttribute("proj_list", proj_list);
						
			url="/page?page=searchAll";
		}
		else if(cmd.equals("PFTAG")){
			String tag_name = req.getParameter("tag_name");
			System.out.println(tag_name);
			tag_name = tag_name.toUpperCase();
			List port_list = searchDao.searchAll_port(tag_name,true);
			
			//Model에서 가지고 온 정보를 View에 넘겨주기 위해 변수 선언
			req.setAttribute("port_list", port_list);
			
			url="/page?page=pfSearch";
		}
		else if(cmd.equals("MEMTAG")){
			String tag_name = req.getParameter("tag_name");
			System.out.println(tag_name);
			tag_name = tag_name.toUpperCase();
			List mem_list = searchDao.searchAll_member(tag_name,true);
			
			//Model에서 가지고 온 정보를 View에 넘겨주기 위해 변수 선언
			req.setAttribute("mem_list", mem_list);
			
			url="/page?page=memSearch";
		}
		else if(cmd.equals("PROJTAG")){
			String tag_name = req.getParameter("tag_name");
			System.out.println(tag_name);
			tag_name = tag_name.toUpperCase();
			List proj_list = searchDao.searchAll_proj(tag_name,true);	
			
			//Model에서 가지고 온 정보를 View에 넘겨주기 위해 변수 선언
			req.setAttribute("proj_list", proj_list);			
			
			url="/page?page=projSearch";
		}
		

		RequestDispatcher view = req.getRequestDispatcher(url);
		view.forward(req,resp);
	}
}
package portit.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import portit.model.dao.SearchDao;
import portit.model.dto.Member;
import portit.model.dto.Portfolio;
/**
 * 
 * @author isyi
 * 검색어를 받아 검색화면에 전달 해주기 위한 컨트롤러
 *
 */

@WebServlet("/search")
public class SearchAllController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		String cmd = req.getParameter("cmd");
		String url = null;
		HttpSession session = req.getSession();
		
		//검색어 저장
		String search = req.getParameter("search");
		search = search.toUpperCase();
		session.setAttribute("search", search);
		
		boolean lineup = true;
		
		//dao 호출
		SearchDao searchDao = new SearchDao();		
		List port_list = searchDao.searchAll_port(search,lineup);
		List mem_list = searchDao.searchAll_member(search,lineup);
		List proj_list = searchDao.searchAll_proj(search,lineup);
	
		List mem_list_tag = searchDao.searchAll_member_tag(search,lineup);
		List proj_list_tag = searchDao.searchAll_proj_tag(search,lineup);
	
		//Model에서 가지고 온 정보를 View에 넘겨주기 위해 변수 선언
		req.setAttribute("port_list", port_list);
		req.setAttribute("mem_list", mem_list);
		req.setAttribute("proj_list", proj_list);			
			
		req.setAttribute("mem_list_tag", mem_list_tag);			
		req.setAttribute("proj_list_tag", proj_list_tag);			
		
		
		if(cmd.equals("SEARCH")){
			url="/page?page=searchAll";
		}

		RequestDispatcher view = req.getRequestDispatcher(url);
		view.forward(req,resp);
	}
}
package portit.controller;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import portit.model.dao.SearchDao;
import portit.model.dto.Portfolio;


@WebServlet(urlPatterns="/detailSearch")
public class SearchDetailController extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		
		String cmd = req.getParameter("cmd");
		String url = null;
		
		//검색어 결과저장
		String pfSearch = req.getParameter("pfSearch");
		String memSearch = req.getParameter("memSearch");
		String projSearch = req.getParameter("projSearch");
		//pfSearch = pfSearch.toUpperCase();
		
		//1~6까지의 값을 불러옴 1,3,5는 최신순 정렬 2,4,6은 인기순 정렬
		int list_value=Integer.parseInt(req.getParameter("list_value"));
		
		//lineup = true(최신순) / false(인기순)
		boolean lineup = true;
	
		SearchDao dao = new SearchDao();
		if(list_value == 1){
			req.setAttribute("port_list",dao.searchAll_port(pfSearch, lineup));
		}		
		else if(list_value == 2){
			req.setAttribute("port_list",dao.searchAll_port(pfSearch, !lineup));		
		}
		else if(list_value == 3){
			req.setAttribute("mem_list", dao.searchAll_member(memSearch, lineup));
		}
		else if(list_value == 4){
			req.setAttribute("mem_list", dao.searchAll_member(memSearch, !lineup));
		}
		else if(list_value == 5){
			req.setAttribute("proj_list", dao.searchAll_proj(projSearch, lineup));
		}
		else if(list_value == 6){
			req.setAttribute("proj_list", dao.searchAll_proj(projSearch, !lineup));
		}
		
		
		if(cmd.equals("PFDETAIL")){
			url="/page?page=pfSearch";
		}
		else if(cmd.equals("MEMDETAIL")){
			url="/page?page=memSearch";
		}
		else if(cmd.equals("PROJDETAIL")){
			url="/page?page=projSearch";
		}

		RequestDispatcher view = req.getRequestDispatcher(url);
		view.forward(req,resp);
	}
}
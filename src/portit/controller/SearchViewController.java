package portit.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import portit.model.dao.SearchDao;


@WebServlet(urlPatterns="/SearchView")
public class SearchViewController extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		String cmd = req.getParameter("cmd");
		String url = null;
		HttpSession session = req.getSession();
		
		
		
		//검색어 결과 저장
		String list2 = req.getParameter("list2");	//통합 검색한 결과
		String pfSearch = req.getParameter("pfSearch");	//포트폴리오 페이지 내에서 검색 결과 값
		String memSearch = req.getParameter("memSearch");  // 멤버 페이지 내에서 검색 결과 값
		String projSearch = req.getParameter("projSearch");	 // 프로젝트 페이지 내에서 검색 결과 값
		
		
		//포트폴리오 Dao 호출
		//Controller -> Model로 넘겨주기
		SearchDao searchDao = new SearchDao();
		List list, list1;
		
		//true = 최신순 , false = 인기순
		boolean lineup = true;				
		
		//통합검색 검색, 멤버 페이지내의 검색어, 프로젝트 내의 검색어가 없는 경우(포트폴리오 페이지 내에서 검색어를 입력하고 검색한 경우)
		if(list2 == null && memSearch==null && projSearch==null){
			session.removeAttribute("search");
			pfSearch = pfSearch.toUpperCase();
			list = searchDao.searchAll_port(pfSearch,lineup);
			list1 = searchDao.searchAll_port_tag(pfSearch,lineup);
			req.setAttribute("port_list", list);
			req.setAttribute("port_list_tag", list1);
		}
		//멤버 페이지에서 검색한 경우
		else if(list2 == null &&pfSearch==null && projSearch==null){
			session.removeAttribute("search");
			memSearch = memSearch.toUpperCase();
			list = searchDao.searchAll_member(memSearch,lineup);
			list1 = searchDao.searchAll_member_tag(memSearch, lineup);
			req.setAttribute("mem_list", list);
			req.setAttribute("mem_list_tag", list1);
		}
		//프로젝트 페이지에서 검색한 경우
		else if(list2 == null && pfSearch==null && memSearch==null){
			session.removeAttribute("search");
			projSearch = projSearch.toUpperCase();
			list = searchDao.searchAll_proj(projSearch,lineup);
			list1 = searchDao.searchAll_proj_tag(projSearch, lineup);
			req.setAttribute("proj_list", list);
			req.setAttribute("proj_list_tag", list1);
		}	
		
		//통합검색의 후, 더 보기 버튼을 눌렀을 때, 통합검색한 검색어를 가지고 포트폴리오 페이지로 이동
		if(cmd.equals("PFSEARCH")){		
			if(list2 != null){				
				list = searchDao.searchAll_port(list2,lineup);	
				list1 = searchDao.searchAll_port_tag(pfSearch,lineup);
				req.setAttribute("port_list", list);
				req.setAttribute("mem_list_tag", list1);
			}			
			url="/page?page=pfSearch";
		}
		else if(cmd.equals("MEMSEARCH")){
			if(list2 != null){
				list = searchDao.searchAll_member(list2,lineup);
				list1 = searchDao.searchAll_member_tag(memSearch, lineup);
				req.setAttribute("mem_list", list);
				req.setAttribute("mem_list_tag", list1);
			}			
			url="/page?page=memSearch";
		}
		else if(cmd.equals("PROJSEARCH")){
			if(list2 != null){
				list = searchDao.searchAll_proj(list2,lineup);
				list1 = searchDao.searchAll_proj_tag(projSearch, lineup);
				req.setAttribute("proj_list", list);
				req.setAttribute("proj_list_tag", list1);
			}
			url="/page?page=projSearch";
		}

		RequestDispatcher view = req.getRequestDispatcher(url);
		view.forward(req,resp);
	}
}

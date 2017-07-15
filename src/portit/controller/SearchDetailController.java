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
		
		SearchDao searchDao = new SearchDao();
		//검색어 결과저장
		
		//String pfSearch = req.getParameter("pfSearch");
		//String memSearch = req.getParameter("memSearch");
		//String projSearch = req.getParameter("projSearch");
		//pfSearch = pfSearch.toUpperCase();
	
		//1~6까지의 값을 불러옴 1,3,5는 최신순 정렬 2,4,6은 인기순 정렬
		int list_value=0;
		
		//오류 수정..
		String list_value_param = req.getParameter("list_value");
		if(!"".equals(list_value_param)){
			System.out.println("값전달 확인12  "+list_value_param);
			list_value=Integer.parseInt(list_value_param);
		}
		
		List list = null;
		//lineup = true(최신순) / false(인기순)
		boolean lineup = true;
	
		SearchDao dao = new SearchDao();
		
		String param1=req.getParameter("param1");
		String param2=req.getParameter("param2");
		String param3=req.getParameter("param3");
		String param4=req.getParameter("param4");

		
		if(cmd.equals("PFDETAIL")){
			String pfSearch = (String) session.getAttribute("pfSearch");
			System.out.println("CTRL : PFDETAIL 접근확인");
			
			
			System.out.println("CTRL 파라미터전달확인="+param1);
			System.out.println("CTRL 파라미터전달확인22="+param2);
			System.out.println("CTRL 파라미터전달확인33="+param3);
			System.out.println("CTRL 파라미터전달확인44="+param4);
	
			
			//다중검색
			if(list_value == 0 ){
				//검색어 없을시
				if(param1 ==null && param2 == null && param3 == null ){
					list = searchDao.searchAll_port(" ", true);
				}
				//검색태그 1개
				else if(param1 != null && param2 ==null && param3 == null ){
					list = searchDao.searchAll_port(param1, true);	
					req.setAttribute("port_list", list);
				}
				//검색태그 2개
				else if(param1 != null && param2 != null && param3 == null ){
					list = searchDao.searchAll_port(param1, param2);
					req.setAttribute("port_list", list);
				}
				//검색태그 3개
				else{
					list = searchDao.searchAll_port(param1, param2, param3);
					req.setAttribute("port_list", list);
				}
			}
			
			
			//정렬
			//list_value = Integer.parseInt(req.getParameter("list_value"));	
			
			// 1 : 최신순 , 2 : 인기순
			else if(list_value == 1){
				req.setAttribute("pfSearch", pfSearch);
				list = dao.searchAll_port(pfSearch, lineup);
				req.setAttribute("port_list", list);
			}
			else if(list_value == 2){
				req.setAttribute("pfSearch", pfSearch);
				list = dao.searchAll_port(pfSearch, !lineup);
				req.setAttribute("port_list", list);	
			}			
			
			url="/page?page=pfSearch";
		}
		
		else if(cmd.equals("MEMDETAIL")){
			String memSearch = (String) session.getAttribute("memSearch");

			System.out.println("CTRL 파라미터전달확인="+param1);
			System.out.println("CTRL 파라미터전달확인22="+param2);
			System.out.println("CTRL 파라미터전달확인33="+param3);
			System.out.println("CTRL 파라미터전달확인44="+param4);
			
			//list = searchDao.searchAll_member(param, true);
			//req.setAttribute("mem_list", list);
			
			//다중검색
			if(list_value == 0 ){
				//검색어 없을시
				if(param1 ==null && param2 == null && param3 == null ){
					list = searchDao.searchAll_member(" ", true);
					req.setAttribute("mem_list", list);
				}
				//검색태그 1개
				else if(param1 != null && param2 ==null && param3 == null ){
					list = searchDao.searchAll_member(param1, true);	
					req.setAttribute("mem_list", list);
				}
				//검색태그 2개
				else if(param1 != null && param2 != null && param3 == null ){
					list = searchDao.searchAll_member(param1, param2);
					req.setAttribute("mem_list", list);
				}
				//검색태그 3개
				else{
					list = searchDao.searchAll_member(param1, param2, param3);
					req.setAttribute("mem_list", list);
				}
			}
			
			// 3 : 최신순 , 4 : 인기순
			if(list_value == 3){
				req.setAttribute("memSearch", memSearch);
				list = dao.searchAll_port(memSearch, lineup);
				req.setAttribute("mem_list", list);
			}
			else if(list_value == 4){
				req.setAttribute("memSearch", memSearch);
				list = dao.searchAll_port(memSearch, !lineup);
				req.setAttribute("mem_list", list);	
			}		
			
			url="/page?page=memSearch";
		}
		else if(cmd.equals("PROJDETAIL")){
			String projSearch = (String) session.getAttribute("projSearch");
			//list = searchDao.searchAll_member(param, true);
			//req.setAttribute("proj_list", list);
			
			//다중검색
			if(list_value == 0 ){
				//검색어 없을시
				if(param1 ==null && param2 == null && param3 == null ){
					list = searchDao.searchAll_proj(" ", true);
					req.setAttribute("proj_list", list);
				}
				//검색태그 1개
				else if(param1 != null && param2 ==null && param3 == null ){
					list = searchDao.searchAll_proj(param1, true);	
					req.setAttribute("proj_list", list);
				}
				//검색태그 2개
				else if(param1 != null && param2 != null && param3 == null ){
					list = searchDao.searchAll_proj(param1, param2);
					req.setAttribute("proj_list", list);
				}
				//검색태그 3개
				else{
					list = searchDao.searchAll_proj(param1, param2, param3);
					req.setAttribute("proj_list", list);
				}
			}			
			
			
			//5 : 최신순 , 6:d-day임박 순
			if(list_value == 5){
				req.setAttribute("projSearch", projSearch);
				list = dao.searchAll_port(projSearch, lineup);
				req.setAttribute("porj_list", list);
			}
			else if(list_value == 6){
				req.setAttribute("projSearch", projSearch);
				list = dao.searchAll_port(projSearch, !lineup);
				req.setAttribute("proj_list", list);	
			}	
			url="page?page=projSearch";
		}

		RequestDispatcher view = req.getRequestDispatcher(url);
		view.forward(req,resp);
	}
}
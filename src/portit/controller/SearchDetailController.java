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
		
		System.out.println("CTRL: detailSearch 접근");
		
		String cmd = req.getParameter("cmd");
		String url = null;
		
		SearchDao searchDao = new SearchDao();
		//검색어 결과저장
		String pfSearch = req.getParameter("pfSearch");
		String memSearch = req.getParameter("memSearch");
		String projSearch = req.getParameter("projSearch");
		//pfSearch = pfSearch.toUpperCase();
		
		//1~6까지의 값을 불러옴 1,3,5는 최신순 정렬 2,4,6은 인기순 정렬
		int list_value=-1;
		
		//오류 수정..
		String list_value_param = req.getParameter("list_value");
		if(!"".equals(list_value_param)){
			System.out.println("값전달 확인12  "+list_value_param);
			list_value=Integer.parseInt(list_value_param);
		}
		
		
		//lineup = true(최신순) / false(인기순)
		boolean lineup = true;
	
		SearchDao dao = new SearchDao();
		if(list_value == 1){
			req.setAttribute("port_list",dao.searchAll_port("", lineup));
		}		
		else if(list_value == 2){
			req.setAttribute("port_list",dao.searchAll_port("", !lineup));		
		}
		else if(list_value == 3){
			req.setAttribute("mem_list", dao.searchAll_member("", lineup));
		}
		else if(list_value == 4){
			req.setAttribute("mem_list", dao.searchAll_member("", !lineup));
		}
		else if(list_value == 5){
			req.setAttribute("proj_list", dao.searchAll_proj("", lineup));
		}
		else if(list_value == 6){
			req.setAttribute("proj_list", dao.searchAll_proj("", !lineup));
		}
		
		
		if(cmd.equals("PFDETAIL")){
			System.out.println("CTRL : PFDETAIL 접근확인");
			
			String param=req.getParameter("param1");
			String param2=req.getParameter("test3");
			
			System.out.println("CTRL 파라미터전달확인="+param);
			System.out.println("CTRL 파라미터전달확인22="+param2);
			List list = searchDao.searchAll_port(param, true);
			req.setAttribute("port_list", list);
			//전달받는 파라미터 확인하기.
			
			
			url="/page?page=pfSearch";
		}
		else if(cmd.equals("MEMDETAIL")){
			String param=req.getParameter("param1");
			List list = searchDao.searchAll_member(param, true);
			req.setAttribute("mem_list", list);
			
			url="/page?page=memSearch";
		}
		else if(cmd.equals("PROJDETAIL")){
			String param=req.getParameter("param1");
			List list = searchDao.searchAll_member(param, true);
			req.setAttribute("proj_list", list);
			
			url="page?page=projSearch";
		}

		RequestDispatcher view = req.getRequestDispatcher(url);
		view.forward(req,resp);
	}
}
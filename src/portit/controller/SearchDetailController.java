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
	
		
		String param1=req.getParameter("param1");
		String param2=req.getParameter("param2");
		String param3=req.getParameter("param3");
		String param4=req.getParameter("language2");
		System.out.println("param1 : " +param1);
		System.out.println("param4 : " +param4);
		param4 = param4.toUpperCase();
		
		
		//  포트폴리오 정렬 및 / 다중 검색
		if(cmd.equals("PFDETAIL")){
			String pfSearch = (String) session.getAttribute("pfSearch");
			if(!"".equals(pfSearch) ){
				pfSearch = pfSearch.toUpperCase();
			}
			System.out.println("CTRL : PFDETAIL 접근확인");
			
			
			System.out.println("CTRL 파라미터전달확인="+param1);
			System.out.println("CTRL 파라미터전달확인22="+param2);
			System.out.println("CTRL 파라미터전달확인33="+param3);
			System.out.println("CTRL 파라미터전달확인44="+param4);
	
			
			//다중검색
			if(list_value == 0 ){
				//검색어 없을시
				//if(param1 ==null && param2 == null && param3 == null && param4 == "" ){
				if("".equals(param1)  && "".equals(param2) && "".equals(param3) && "".equals(param4)){
					list = searchDao.searchAll_port(" ", true);
					req.setAttribute("port_list", list);
				}
				//검색태그 1개 (버튼 1개로 검색)
				else if(param1 != null && param2 ==null && param3 == null && param4 == ""  ){
					list = searchDao.searchAll_port(param1, true);	
					req.setAttribute("port_list", list);
				}
				//검색태그 1개 (텍스트박스로 검색)
				else if(param1 == null && param2 ==null && param3 == null && param4 != "" ){
					list = searchDao.searchAll_port(param4, true);	
					req.setAttribute("port_list", list);
				}
				//검색태그 2개 (버튼 2개로 검색)
				else if(param1 != null && param2 != null && param3 == null && param4 == ""){
					list = searchDao.searchAll_port(param1, param2);
					req.setAttribute("port_list", list);
				}
				//검색태그 2개 (버튼 1개 + 텍스트 1개로 검색)
				else if(param1 != null && param2 == null && param3 == null &&  param4 != "" ){
					list = searchDao.searchAll_port(param1, param4);
					req.setAttribute("port_list", list);
				}
				//검색태그 3개 (버튼 3개로 검색)
				else if(param1 != null && param2 != null && param3 != null && param4 == "" ){
					list = searchDao.searchAll_port(param1, param2, param3);
					req.setAttribute("port_list", list);
				}
				//검색태그 3개 (버튼 2개 + 텍스트로 검색)
				else if(param1 != null && param2 != null && param3 == null &&  param4 != "" ){
					list = searchDao.searchAll_port(param1, param2, param4);
					req.setAttribute("port_list", list);
				}
			}
			
			
			//정렬
			//list_value = Integer.parseInt(req.getParameter("list_value"));	
			
			// 1 : 최신순 , 2 : 인기순
			else if(list_value == 1){
				req.setAttribute("pfSearch", pfSearch);
				System.out.println("pfsearch ??? " + pfSearch);				
				list =  searchDao.searchAll_port(pfSearch, lineup);
				req.setAttribute("port_list", list);
			}
			else if(list_value == 2){
				req.setAttribute("pfSearch", pfSearch);
				System.out.println("pfsearch ..? " + pfSearch);
				list = searchDao.searchAll_port(pfSearch, !lineup);
				req.setAttribute("port_list", list);	
			}			
			
			url="/page?page=pfSearch";
		}
		
		else if(cmd.equals("MEMDETAIL")){
			String memSearch = (String) session.getAttribute("memSearch");
			if(memSearch !=null){
				memSearch = memSearch.toUpperCase();
			}
			System.out.println("CTRL 파라미터전달확인="+param1);
			System.out.println("CTRL 파라미터전달확인22="+param2);
			System.out.println("CTRL 파라미터전달확인33="+param3);
			System.out.println("CTRL 파라미터전달확인44="+param4);
			
			
			
			//다중검색
			if(list_value == 0 ){
				//검색어 없을시
				if(param1 ==null && param2 == null && param3 == null && param4 == ""){
					list = searchDao.searchAll_member(" ", true);
					req.setAttribute("mem_list", list);
				}
				//검색태그 1개 (버튼 1개로 검색)
				else if(param1 != null && param2 ==null && param3 == null && param4 == ""){
					list = searchDao.searchAll_member(param1, true);	
					req.setAttribute("mem_list", list);
				}
				//검색태그 1개 (텍스트박스 검색)
				else if(param1 == null && param2 ==null && param3 == null && param4 != ""){
					list = searchDao.searchAll_member(param4, true);	
					req.setAttribute("mem_list", list);
				}
				//검색태그 2개 (버튼 2개 클릭으로 검색)
				else if(param1 != null && param2 != null && param3 == null && param4 == ""){
					list = searchDao.searchAll_member(param1, param2);
					req.setAttribute("mem_list", list);
				}
				//검색태그 2개 (버튼 1개 + 텍스트박스 검색)
				else if(param1 != null && param2 == null && param3 == null && param4 != ""){
					list = searchDao.searchAll_member(param1, param4);
					req.setAttribute("mem_list", list);
				}
				//검색태그 3개(버튼 3개로 검색)
				else if(param1 != null && param2 != null && param3 != null && param4 == ""){
					list = searchDao.searchAll_member(param1, param2, param3);
					req.setAttribute("mem_list", list);
				}
				//검색태그 3개(버튼 2개 + 텍스트박스로 검색)
				else if(param1 != null && param2 != null && param3 == null && param4 != ""){
					list = searchDao.searchAll_member(param1, param2, param4);
					req.setAttribute("mem_list", list);
				}
			}
			
			// 3 : 최신순 , 4 : 인기순
			else if(list_value == 3){
				req.setAttribute("memSearch", memSearch);
				
				list = searchDao.searchAll_member(memSearch, lineup);
				req.setAttribute("mem_list", list);
			}
			else if(list_value == 4){
				req.setAttribute("memSearch", memSearch);
				
				list = searchDao.searchAll_member(memSearch, !lineup);
				req.setAttribute("mem_list", list);	
			}		
			
			url="/page?page=memSearch";
		}
		else if(cmd.equals("PROJDETAIL")){
			String projSearch = (String) session.getAttribute("projSearch");
			projSearch = projSearch.toUpperCase();	
			
			//다중검색
			if(list_value == 0 ){
				//검색어 없을시
				if(param1 ==null && param2 == null && param3 == null && param4 == "" ){
					list = searchDao.searchAll_proj(" ", true);
					req.setAttribute("proj_list", list);
				}
				//검색태그 1개 (버튼 클릭으로 검색)
				else if(param1 != null && param2 ==null && param3 == null && param4 == "" ){
					list = searchDao.searchAll_proj(param1, true);	
					req.setAttribute("proj_list", list);
				}
				//검색태그 1개 (텍스트로 검색)
				else if(param1 == null && param2 ==null && param3 == null && param4 != "" ){
					list = searchDao.searchAll_proj(param4, true);	
					req.setAttribute("proj_list", list);
				}
				//검색태그 2개(버튼 2개로 검색)
				else if(param1 != null && param2 != null && param3 == null  && param4 == ""){
					list = searchDao.searchAll_proj(param1, param2);
					req.setAttribute("proj_list", list);
				}
				//검색태그 2개(버튼 1개 + 텍스트로 검색)
				else if(param1 != null && param2 == null && param3 == null  && param4 != ""){
					list = searchDao.searchAll_proj(param1, param4);
					req.setAttribute("proj_list", list);
				}
				//검색태그 3개(버튼 3개로 검색)
				else if(param1 != null && param2 != null && param3 != null  && param4 == ""){
					list = searchDao.searchAll_proj(param1, param2, param3);
					req.setAttribute("proj_list", list);
				}
				//검색태그 3개(버튼 2개 + 텍스트로 검색)
				else if(param1 != null && param2 != null && param3 == null  && param4 != ""){
					list = searchDao.searchAll_proj(param1, param2, param4);
					req.setAttribute("proj_list", list);
				}
			}			
			
			
			//5 : 최신순 , 6:d-day임박 순
			else if(list_value == 5){
				req.setAttribute("projSearch", projSearch);
				System.out.println("projSearch5: " + projSearch);
				list = searchDao.searchAll_proj(projSearch, lineup);
				req.setAttribute("proj_list", list);
			}
			else if(list_value == 6){
				req.setAttribute("projSearch", projSearch);
				System.out.println("projSearch6: " + projSearch);
				list = searchDao.searchAll_proj(projSearch, !lineup);
				req.setAttribute("proj_list", list);	
			}	
			url="page?page=projSearch";
		}
		else if(cmd.equals("SEARCHALL")){
			List list1 = new ArrayList();
			List list2 = new ArrayList();
			List list3 = new ArrayList();
			
			System.out.println("CTRL 파라미터전달확인="+param1);
			System.out.println("CTRL 파라미터전달확인22="+param2);
			System.out.println("CTRL 파라미터전달확인33="+param3);
			System.out.println("CTRL 파라미터전달확인44="+param4);
			
			//검색어 없을시
			if(param1 ==null && param2 == null && param3 == null && param4 == ""){
				list1 = searchDao.searchAll_port(" ", true);
				list2 = searchDao.searchAll_member(" ", true);
				list3 = searchDao.searchAll_proj(" ", true);
				
				req.setAttribute("port_list", list1);
				req.setAttribute("mem_list", list2);
				req.setAttribute("proj_list", list3);				
			}
			//검색태그 1개 (버튼눌러서 검색)
			else if(param1 != null && param2 ==null && param3 == null && param4 == ""){
				list1 = searchDao.searchAll_port(param1, true);	
				list2 = searchDao.searchAll_member(param1, true);	
				list3 = searchDao.searchAll_proj(param1, true);	
				
				req.setAttribute("port_list", list1);
				req.setAttribute("mem_list", list2);
				req.setAttribute("proj_list", list3);
			}
			//검색태그 1개 (검색어로 검색)
			else if(param1 == null && param2 ==null && param3 == null && param4 != "" ){
				list1 = searchDao.searchAll_port(param4, true);	
				list2 = searchDao.searchAll_member(param4, true);	
				list3 = searchDao.searchAll_proj(param4, true);	
				
				req.setAttribute("port_list", list1);
				req.setAttribute("mem_list", list2);
				req.setAttribute("proj_list", list3);
			}
			//검색태그 2개 (버튼 2개 클릭해서 검색)
			else if(param1 != null && param2 != null && param3 == null && param4 == "" ){
				list1 = searchDao.searchAll_port(param1, param2);
				list2 = searchDao.searchAll_member(param1, param2);
				list3 = searchDao.searchAll_proj(param1, param2);
				
				req.setAttribute("port_list", list1);
				req.setAttribute("mem_list", list2);
				req.setAttribute("proj_list", list3);
			}
			//검색태그 2개 (버튼1 + 텍스트 1)
			else if(param1 != null && param2 != null && param3 == null && param4 != ""){
				list1 = searchDao.searchAll_port(param1, param4);
				list2 = searchDao.searchAll_member(param1, param4);
				list3 = searchDao.searchAll_proj(param1, param4);
				
				req.setAttribute("port_list", list1);
				req.setAttribute("mem_list", list2);
				req.setAttribute("proj_list", list3);
			}
			//검색태그 3개(버튼 3개)
			else if(param1 != null && param2 != null && param3 != null && param4 == ""){
				list1 = searchDao.searchAll_port(param1, param2, param3);
				list2 = searchDao.searchAll_member(param1, param2, param3);
				list3 = searchDao.searchAll_proj(param1, param2, param3);
				
				req.setAttribute("port_list", list1);
				req.setAttribute("mem_list", list2);
				req.setAttribute("proj_list", list3);
			}
			//검색태그 3개(버튼 2개 + 텍스트 1)
			else if(param1 != null && param2 != null && param3 == null && param4 != ""){
				list1 = searchDao.searchAll_port(param1, param2, param4);
				list2 = searchDao.searchAll_member(param1, param2, param4);
				list3 = searchDao.searchAll_proj(param1, param2, param4);
				
				req.setAttribute("port_list", list1);
				req.setAttribute("mem_list", list2);
				req.setAttribute("proj_list", list3);
			}
			
			url="page?page=searchAll";
		}

		RequestDispatcher view = req.getRequestDispatcher(url);
		view.forward(req,resp);
	}
}
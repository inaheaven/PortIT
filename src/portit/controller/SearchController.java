package portit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/search")
public class SearchController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		String cmd = req.getParameter("cmd");
		String url = null;
		
		//검색한 검색어를 저장하는 변수
		String search_name = req.getParameter("search");	
		
		if(cmd.equals("SEARCH")){
			url="/searchAll.jsp";
		}

		RequestDispatcher view = req.getRequestDispatcher(url);
		view.forward(req,resp);
	}
}

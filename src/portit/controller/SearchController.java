package portit.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/join")
public class SearchController extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		System.out.println("Servlet Loaded");
		resp.setContentType("text/html; charset=UTF-8");
		
		String cmd = req.getParameter("cmd");
		String url = null;	
	
		
		if(cmd.equals("SEARCH")){
			url="search.html";
		}
		RequestDispatcher view = req.getRequestDispatcher(url);
		view.forward(req,resp);
	}
}

package portit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import portit.model.dao.ProjectDao;

public class ProjectUpdateController extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
				
		System.out.println("Servlet Loaded");
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		String proj_id = req.getParameter("proj_id");
		System.out.println(proj_id+"!!!!!");

		ProjectDao dao = new ProjectDao();
		
		
		dao.read_proj(req, resp);

		String test = req.getParameter("proj_numofperson");
		System.out.println(test+"!!!!");
		
		/*
		String page = req.getParameter("page");
		String pageName = page + ".jsp";
		req.setAttribute("pageName", pageName);
		RequestDispatcher view = req.getRequestDispatcher("/template.jsp");
		view.forward(req, resp);
		*/
	}
}

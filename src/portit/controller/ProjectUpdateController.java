package portit.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import portit.model.dao.ProjectDao;
import portit.model.dto.Project;

public class ProjectUpdateController extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
				
		System.out.println("Servlet Loaded");
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		String proj_id = req.getParameter("proj_id");

		ProjectDao dao = new ProjectDao();
		ArrayList<Project> list = new ArrayList<>();
		dao.read_proj(req, resp);
		
		
		
		/*
		String page = req.getParameter("page");
		String pageName = page + ".jsp";
		req.setAttribute("pageName", pageName);
		RequestDispatcher view = req.getRequestDispatcher("/template.jsp");
		view.forward(req, resp);
		*/
	}
}

package portit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import portit.model.dao.ProjectDao;

public class ProjectDeleteController extends HttpServlet {
   
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
      doPost(req, resp);
   }

   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
      
            
      System.out.println("삭제Servlet Loaded");
      resp.setContentType("text/html; charset=UTF-8");
      req.setCharacterEncoding("UTF-8");
      ProjectDao dao = new ProjectDao();
      dao.delete_pro(req, resp);
      
      resp.sendRedirect("myProjList.jsp");
   }

}
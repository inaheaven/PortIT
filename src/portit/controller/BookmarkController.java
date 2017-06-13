package portit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import portit.model.dao.BookmarkDao;

public class BookmarkController extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doPost(req, resp);
		BookmarkDao bookmarkDao = new BookmarkDao();
		return bookmarkDao.getBookmark(req., mem_id);
		
		
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		System.out.println("Servlet Loaded");
		resp.setContentType("text/html; charset=UTF-8");
	}

}
